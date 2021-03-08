package cn.net.yzl.logger.aspect;

import cn.net.yzl.common.entity.GeneralResult;
import cn.net.yzl.common.enums.ResponseCodeEnums;
import cn.net.yzl.logger.Log;
import cn.net.yzl.logger.common.XBasicUtil;
import cn.net.yzl.logger.common.XContextUtil;
import cn.net.yzl.logger.common.XDateUtil;
import cn.net.yzl.logger.enums.DefaultDataEnums;
import cn.net.yzl.logger.exception.JoinPointProceedException;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @title: 系统访问日志 AOP
 * @Author lichanghong
 * @Date: 2020/11/30 9:23 上午
 * @Version 1.0
 */
@Aspect
@Component
@Order(value = 1)
@Slf4j
@RefreshScope
public class ApiAccessAspect {
    @Value("${logger.common.switch.logger.monitor:1}")
    private Integer monitorSwitch;

    @Value("${logger.common.switch.logger.outApi:1}")
    private Integer outApiSwitch;
    @Around(value = "execution(@org.springframework.web.bind.annotation.RequestMapping * *(..)) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{

        Log.traceIdOf(DefaultDataEnums.Source.OUT_API);

        //返回值
        Object returnValue = null;
        String requestURI = "";
        try {
            //先初始化入参，为了处理上游业务传过来的uuid
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
			// 解决非web服务下收集日志报错的问题，例如：在MQ消费者中调用feign接口，此时MQ触发机制并非http请求，是无法获取请求参数的。
			if (requestAttributes == null) {
				return joinPoint.proceed();
			}
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            Map<String, String> requestParams = getRequestParameters(request);
            Long startEpochMilli = XDateUtil.getCurrentEpochMilli();
            String startDate = XDateUtil.getCurrentUtcStringSDate(true);
            /*
             *1、从请求中获取traceId,
             *2、如果存在就更改traceId
             */
            String traceId = request.getHeader("traceId");
            if(StringUtils.isNotEmpty(traceId)){
                MDC.put("traceId",traceId);
            }

            try {
                returnValue = joinPoint.proceed();
            } catch (Exception e) {
                throw new JoinPointProceedException(e);
            }

            if (outApiSwitch.equals(0) && monitorSwitch.equals(0)){
                return returnValue;
            }

            Long endEpochMilli = XDateUtil.getCurrentEpochMilli();
            String endDate = XDateUtil.getCurrentUtcStringSDate(true);
            long time = endEpochMilli - startEpochMilli;

            if (XBasicUtil.ignoreRequest(request)){
                return returnValue;
            }

            requestURI = request.getRequestURI();

            String clientIp = XBasicUtil.getClientIp(request);

            int endIndex = request.getRequestURL().length() - request.getRequestURI().length();
            String domain = request.getRequestURL().indexOf("http://") == 0 ?
                    request.getRequestURL().substring(7, endIndex) :
                    request.getRequestURL().substring(0, endIndex);

            String requestMethod = request.getMethod();

            String args = Joiner.on("&").useForNull("").withKeyValueSeparator("=").join(requestParams);

            Boolean isSuccess = true;
            Integer code = 0;
            String message = "";
            DefaultDataEnums.Level level = DefaultDataEnums.Level.INFO;
            if (returnValue != null && returnValue.getClass().equals(GeneralResult.class)){
                message = ((GeneralResult)returnValue).getMessage();
                code = ((GeneralResult)returnValue).getCode();
                if ( !Objects.equals(code ,  ResponseCodeEnums.SUCCESS_CODE.getCode())){
                    level = DefaultDataEnums.Level.ERROR;
                    isSuccess=false;
                }
            }

            if (monitorSwitch.equals(1)){
                Log.monitorLogger(requestURI,DefaultDataEnums.Source.OUT_API.getStatus(),null,String.valueOf(time),domain,
                        startDate,endDate,requestParams.get("appId"), clientIp, code, message, level, requestMethod);
            }

            if (outApiSwitch.equals(1)){
                Log.apiAccessShowLogger(requestURI , DefaultDataEnums.Source.OUT_API.getStatus(),null,
                        requestParams.get("appId"), requestParams.get("ztAppId"),
                       requestParams, returnValue,
                        String.valueOf(time), requestMethod, domain, startDate, endDate,
                        null,null, args, clientIp);

                if (!isSuccess){
                    Log.sysErrorLogger(requestURI , DefaultDataEnums.Source.OUT_API.getStatus(),message,
                            code,null, requestParams, returnValue , String.valueOf(time) , requestMethod , domain);
                }
            }
        }catch (JoinPointProceedException je){
            throw je.getCause();
        } catch (Throwable throwable) {
            //请求的 类名、方法名
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = ((MethodSignature) joinPoint.getSignature()).getName();
            log.error("系统日志切面捕获异常|code: {}| logKey: {} | URI : {} | traceId: {}", ResponseCodeEnums.ASPECT_ERROR.getCode(),
                    className+"."+methodName, requestURI , Log.getTraceId() , throwable);
        }
        return returnValue;
    }

    private static Map<String, String> getRequestParameters(HttpServletRequest request) {
        Map<String, String> rs =  new HashMap<>();

        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String k = names.nextElement();
            rs.put(k, request.getParameter(k));
        }
        //收集通过统一网关过来的请求
        if (!StringUtils.isEmpty(request.getHeader("appId"))){
            rs.put("appId" , request.getHeader("appId"));
        }

        //收集业务方传过来的traceId
        String traceId = request.getHeader("traceId");
        if (!StringUtils.isEmpty(traceId)){
            rs.put("traceId" , traceId);
            MDC.put("traceId" , traceId);
        }

        //收集业务方传过来的spanId
        String spanId = request.getHeader("spanId");
        if (!StringUtils.isEmpty(spanId)){
            rs.put("spanId" , spanId);
            MDC.put("spanId" , spanId);
        }

        Log.resetTraceIdOf(traceId , null, spanId, null);
        return rs;
    }

    @After("execution(@org.springframework.web.bind.annotation.RequestMapping * *(..))")
    public void doAfter(JoinPoint joinPoint) throws Throwable{
        Arrays.asList(DefaultDataEnums.ThreadLocalKeys.values())
                .forEach(threadLocalKeys -> XContextUtil.clear(threadLocalKeys.getKey()));
        MDC.clear();

    }

}
