package cn.net.yzl.operatelogger.aspect;


import cn.net.yzl.operatelogger.annotate.OperateLog;
import cn.net.yzl.operatelogger.common.AspectAnnotationUtil;
import cn.net.yzl.operatelogger.common.ServletUtils;
import cn.net.yzl.operatelogger.common.SpringContextUtil;
import cn.net.yzl.operatelogger.common.str.StringUtils;
import cn.net.yzl.operatelogger.enums.BusinessStatus;
import cn.net.yzl.operatelogger.model.OperateLogModel;
import cn.net.yzl.operatelogger.model.UserModel;
import cn.net.yzl.operatelogger.service.OperateLogService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;


/**
 * @ClassName OperateLogAspect
 * @Descripion 行为日志切面类
 * @Author liwenjie
 * @Date 2020/12/10 9:57
 **/
@Aspect
@Component
@Order(10)
public class OperateLogAspect
{

    /** 排除参数获取值的敏感属性字段 多字段以,号隔开*/
    @Value("${operatelogger.properies.params:passWord}")
    private String excludePropertiesForParams;

    /** 默认截取返回值jsonResult的数据 */
    @Value("${operatelogger.returning.jsonresult.length:2000}")
    private Integer jsonResultLength;

    /** 默认截取请求参数长度的数据 */
    @Value("${operatelogger.request.param.length:2000}")
    private Integer requestParamStrLength;

    /** 默认截取发生异常后异常消息的长度 */
    @Value("${operatelogger.exception.error.msg.length:2000}")
    private Integer exceptionErrorMsg;

    // 配置织入点
    @Pointcut("@annotation(cn.net.yzl.operatelogger.annotate.OperateLog)")
    public void logPointCut()
    {
    }


    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult)
    {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e)
    {
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult)
    {
        OperateLogModel biOperationLog = new OperateLogModel();
        OperateLogService operateLogService = SpringContextUtil.getBean(OperateLogService.class);
        try
        {
            // 获得注解
            OperateLog controllerLog = AspectAnnotationUtil.getAnnotation(OperateLog.class , joinPoint);
            if (controllerLog == null)
            {
                return;
            }

            //  获取当前用户 各系统实现operateLogService接口
            UserModel biUser = operateLogService.getUser();

            // *========日志=========*//
            biOperationLog.setStatus(BusinessStatus.SUCCESS.ordinal());
            //  获取请求地址
            String ip = operateLogService.getIp(ServletUtils.getRequest() );
            biOperationLog.setOperIp(ip);

            // 设置url 例如：/menu/add
            biOperationLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
            if (biUser != null)
            {
                biOperationLog.setOperUserName(biUser.getUserName());
                biOperationLog.setOperUserId(biUser.getUserNo());
                biOperationLog.setDeptId(biUser.getDeptId() == null ? null : Long.valueOf(biUser.getDeptId().toString()));
                biOperationLog.setDeptName(StringUtils.isEmpty(biUser.getDeptName()) ?  "" : biUser.getDeptName());
            }

            if (e != null)
            {
                biOperationLog.setStatus(BusinessStatus.FAIL.ordinal());
                biOperationLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, exceptionErrorMsg));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            biOperationLog.setMethod(className + "." + methodName + "()");

            // 设置请求方式
            biOperationLog.setRequestMethod(ServletUtils.getRequest().getMethod());

            // 处理设置注解上的参数
            getControllerMethodDescription(controllerLog, biOperationLog);

            // 设置操作时间
            biOperationLog.setOperTime(new Date());

            // 设置返回值
            System.out.println(StringUtils.substring(new ObjectMapper().writeValueAsString(jsonResult), 0, jsonResultLength).length());
            biOperationLog.setJsonResult(StringUtils.substring(new ObjectMapper().writeValueAsString(jsonResult), 0, jsonResultLength));

            // 各业务系统转换行为日志（存库或者其他实现）,实现logService接口
            operateLogService.covertOperateLog(biOperationLog);
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
            operateLogService.printOperateLog(biOperationLog);
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log 日志
     * @param operLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(OperateLog log, OperateLogModel model) throws Exception
    {
        // 操作类型
        model.setOperType(log.businessType().ordinal());
        // 模块名称
        model.setOperModule(log.operModule());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData())
        {
            // 获取参数的信息
            setRequestValue(model);
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(OperateLogModel operLog) throws Exception
    {
        Map<String, String[]> map = ServletUtils.getRequest().getParameterMap();
        if (StringUtils.isNotEmpty(map))
        {
            String params = "";
            if(StringUtils.isEmpty(excludePropertiesForParams))
                params = JSONObject.toJSONString(map);
            else
                params = covertStringToParams(map , excludePropertiesForParams);
            operLog.setOperParam(StringUtils.substring(params, 0, requestParamStrLength));
        }
    }

    private String covertStringToParams(Map<String, String[]> map , String param){
        String []paramArray = {};
        if(!excludePropertiesForParams.contains(",")){
            paramArray = excludePropertiesForParams.split("");
        }else{
            paramArray = excludePropertiesForParams.split(",");
        }
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = new PropertyPreFilters().addFilter();
        excludefilter.addExcludes(paramArray);
        String params = JSONObject.toJSONString(map, excludefilter , SerializerFeature.WriteMapNullValue);
        return params;
    }

}
