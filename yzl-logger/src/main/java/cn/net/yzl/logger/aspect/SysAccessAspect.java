package cn.net.yzl.logger.aspect;

import cn.net.yzl.common.enums.ResponseCodeEnums;
import cn.net.yzl.logger.Log;
import cn.net.yzl.logger.annotate.SysAccessLog;
import cn.net.yzl.logger.common.XBeanUtil;
import cn.net.yzl.logger.common.XContextUtil;
import cn.net.yzl.logger.common.XDateUtil;
import cn.net.yzl.logger.enums.DefaultDataEnums;
import cn.net.yzl.logger.exception.JoinPointProceedException;
import cn.net.yzl.logger.json.JacksonUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @title: 系统日志切面
 * @Author lichanghong
 * @Date: 2020/12/1 1:44 下午
 * @Version 1.0
 */
@Aspect
@Component
@Order
@Slf4j
public class SysAccessAspect {
    @Value("${logger.common.switch.logger.monitor:1}")
    private Integer monitorSwitch;

    @Value("${logger.common.switch.logger.scheduler:0}")
    private Integer schedulerSwitch;
    @Value("${logger.common.switch.logger.es:0}")
    private Integer esSwitch;
    @Value("${logger.common.switch.logger.redis:0}")
    private Integer redisSwitch;
    @Value("${logger.common.switch.logger.db:0}")
    private Integer dbSwitch;
    @Value("${logger.common.switch.logger.mq:0}")
    private Integer mqSwitch;
    @Value("${logger.common.switch.logger.thirdApi:0}")
    private Integer thirdApiSwitch;
    @Value("${logger.common.switch.logger.kafka:0}")
    private Integer kafkaSwitch;
    @Value("${logger.common.switch.logger.memoryCache:0}")
    private Integer memoryCacheSwitch;
    @Around("execution(@cn.net.yzl.logger.annotate.SysAccessLog * *(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //请求的 类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = ((MethodSignature) joinPoint.getSignature()).getName();

        String logKey =  "";
        Object returnValue = null;
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();

            //请求的参数
            Object[] args = joinPoint.getArgs();
            String[] paramNames = signature.getParameterNames();

            Method method = signature.getMethod();
            SysAccessLog sysAccessLog = method.getAnnotation(SysAccessLog.class);

            if (sysAccessLog == null){
                try {
                    returnValue = joinPoint.proceed();
                } catch (Exception throwable) {
                    throw new JoinPointProceedException(throwable);
                }
                return returnValue;
            }
            //初始化追踪ID
            Log.traceIdOf(sysAccessLog.source());

            //日志开关，防止日志写入太快打满磁盘
            Boolean logSwitch = getLogSwitch(sysAccessLog.source());
            if (!logSwitch && monitorSwitch.equals(0)){
                try {
                    returnValue = joinPoint.proceed();
                } catch (Exception throwable) {
                    throw new JoinPointProceedException(throwable);
                }
                return returnValue;
            }

            //初始化注解参数
            List<String> requestExcludeParamsName = Lists.newArrayList();
            List<String> logKeyParamNameList = Lists.newArrayList();
            if (!StringUtils.isEmpty(sysAccessLog.logKey())){
                logKey = sysAccessLog.logKey();
            }else if (sysAccessLog.logKeyParamName().length > 0){
                logKeyParamNameList = Arrays.asList(sysAccessLog.logKeyParamName());
            }else {
                logKey = className+"."+methodName;
            }

            if (sysAccessLog.requestExcludeParamsName().length > 0){
                requestExcludeParamsName = Arrays.asList(sysAccessLog.requestExcludeParamsName());
            }
            String requestMetod = sysAccessLog.requestMetod();
            String action = sysAccessLog.action().getStatus();
            String source = sysAccessLog.source().getStatus();
            Boolean isRecodeStartLog = sysAccessLog.isRecodeStartLog();
            String traceIdParam = sysAccessLog.traceIdParam();
            Boolean isRecodeResponse = sysAccessLog.isRecodeResponse();

            //处理注解参数：logKeyParam 、requestParam 、traceIdParam
            Map<String , Object> paramMap = Maps.newHashMap();
            StringBuilder logKeyBuilder = new StringBuilder();
            String traceId = null;
            if (args != null && args.length > 0){
                for (int i = 0; i < args.length; i++) {
                    //初始化logKeyParam
                    if (!CollectionUtils.isEmpty(logKeyParamNameList) &&
                            logKeyParamNameList.contains(paramNames[i])){
                        logKeyBuilder.append(args[i]).append("-");
                    }
                    //初始化requestParam
                    if (!CollectionUtils.isEmpty(requestExcludeParamsName)){
                        if(!requestExcludeParamsName.contains(paramNames[i])){
                            paramMap.put(paramNames[i], args[i]);
                        }
                    }else {
                        paramMap.put(paramNames[i], args[i]);
                    }
                    //初始化traceId 使用场景：MQ为了消息生产和消费日志进行关联，消息体里传递traceId
                    if (!StringUtils.isEmpty(traceIdParam) && paramNames[i].equals(traceIdParam)){
                         if (XBeanUtil.isType(Map.class , args[i].getClass())){
                            if (!Objects.nonNull(((Map)args[i]).get("traceId"))){
                                traceId = ((Map)args[i]).get("traceId").toString();
                            }
                        }else if (XBeanUtil.isType(String.class , args[i].getClass())){
                            traceId = args[i].toString();
                        }
                        if (!StringUtils.isEmpty(traceId)){
                            Log.resetTraceIdOf(null , traceId, null, null);
                        }
                    }
                }
            }
            if (StringUtils.isEmpty(logKey) && logKeyBuilder.length() > 0){
                logKey = logKeyBuilder.toString();
            }
            if (logKey.endsWith("-")){
                logKey = logKey.substring(0 , logKey.length()-1);
            }

            //初始化domain ，调用第三方api使用
            String domain = "";
            if (logKey.startsWith("http://")){
                int startIndex = 7;
                int endIndex = logKey.indexOf("/" , 7);
                domain = logKey.substring(startIndex, endIndex);
            }

            Long startEpochMilli = XDateUtil.getCurrentEpochMilli();
            String startDateStr = XDateUtil.getCurrentUtcStringSDate(true);

            //记录进行中日志，只记录开始时间表示进行中，方法执行完成会记录开始时间和结束时间
            if (isRecodeStartLog && logSwitch){
                Log.accessShowLogger(logKey, source, action, paramMap,
                        null , String.valueOf(0) , requestMetod , domain ,
                        startDateStr , null);
            }

            //执行被注解的方法
            try {
                returnValue = joinPoint.proceed();
            } catch (Exception throwable) {
                throw new JoinPointProceedException(throwable);
            }

            Long endEpochMilli = XDateUtil.getCurrentEpochMilli();
            String endDateStr = XDateUtil.getCurrentUtcStringSDate(true);
            long time = endEpochMilli - startEpochMilli;


            if (!monitorSwitch.equals(0)){
                Log.monitorLogger(logKey,source,action,String.valueOf(time),domain, startDateStr, endDateStr,
                        null, null, null,null, null, null);
            }

            if (logSwitch){
                //初始化response
                String response = JacksonUtil.toJsonString(returnValue);
                if (!isRecodeResponse){
                    response = null;
                }

                //记录执行完成日志
                Log.accessShowLogger(logKey, source, action, paramMap,
                        response , String.valueOf(time) , requestMetod , domain ,
                        startDateStr , endDateStr);
            }

        } catch (JoinPointProceedException je){
            throw je.getCause();
        } catch (Exception e) {
            log.error("系统日志切面运行异常|code: {}| logKey: {} |  traceId: {}", ResponseCodeEnums.ASPECT_ERROR.getCode(),
                    className+"."+methodName,  Log.getTraceId() , e);
        }
        return returnValue;
    }

    @After("execution(@cn.net.yzl.logger.annotate.SysAccessLog * *(..))")
    public void doAfter(JoinPoint joinPoint) throws Throwable{
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysAccessLog sysAccessLog = method.getAnnotation(SysAccessLog.class);
        if (sysAccessLog != null &&
                (sysAccessLog.source().equals(DefaultDataEnums.Source.SCHEDULER) ||
                        (sysAccessLog.source().equals(DefaultDataEnums.Source.MQ) &&
                                sysAccessLog.action().equals(DefaultDataEnums.Action.CONSUMER)) ||
                        (sysAccessLog.source().equals(DefaultDataEnums.Source.KAFKA) &&
                                sysAccessLog.action().equals(DefaultDataEnums.Action.CONSUMER)))){
            Arrays.asList(DefaultDataEnums.ThreadLocalKeys.values())
                    .forEach(threadLocalKeys -> XContextUtil.clear(threadLocalKeys.getKey()));
        }
    }

    public Boolean getLogSwitch(DefaultDataEnums.Source source){
        Boolean logSwitch = true;
        if (source == null){
            logSwitch = false;
        }
        if (source.equals(DefaultDataEnums.Source.SCHEDULER) &&
                (schedulerSwitch == null || schedulerSwitch == 0)) {
            logSwitch = false;
        }
        if (source.equals(DefaultDataEnums.Source.ES) &&
                (esSwitch == null || esSwitch == 0)) {
            logSwitch = false;
        }
        if (source.equals(DefaultDataEnums.Source.MQ) &&
                (mqSwitch == null || mqSwitch == 0)) {
            logSwitch = false;
        }
        if (source.equals(DefaultDataEnums.Source.DB) &&
                (dbSwitch == null || dbSwitch == 0)) {
            logSwitch = false;
        }
        if (source.equals(DefaultDataEnums.Source.REDIS) &&
                (redisSwitch == null || redisSwitch == 0)) {
            logSwitch = false;
        }
        if (source.equals(DefaultDataEnums.Source.THIRD_API) &&
                (thirdApiSwitch == null || thirdApiSwitch == 0)) {
            logSwitch = false;
        }
        if (source.equals(DefaultDataEnums.Source.KAFKA) &&
                (kafkaSwitch == null || kafkaSwitch == 0)) {
            logSwitch = false;
        }
        if (source.equals(DefaultDataEnums.Source.MEMORY_CACHE) &&
                (memoryCacheSwitch == null || memoryCacheSwitch == 0)) {
            logSwitch = false;
        }
        return logSwitch;
    }
}
