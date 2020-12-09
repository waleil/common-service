package cn.net.yzl.logger;

import cn.net.yzl.logger.common.XBasicUtil;
import cn.net.yzl.logger.common.XContextUtil;
import cn.net.yzl.logger.common.XDateUtil;
import cn.net.yzl.logger.common.XMapUtil;
import cn.net.yzl.logger.enums.DefaultDataEnums;
import cn.net.yzl.logger.json.JacksonUtil;
import cn.net.yzl.logger.model.LogModel;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: Log
 * @Author lichanghong
 * @Date: 2020/11/30 9:25 上午
 * @Version 1.0
 */
@Component
@Slf4j
public class Log {
    @Value("${spring.application.name:default}")
    private String APPNAME;

    public static final String MONITOR_LOG_NAME = "monitorLogger";     // 监控日志
    public static final String SYS_ERROR_LOG_NAME = "sysErrorLogger";     // 统一格式的标准错误日志
    public static final String ACCESS_SHOW_LOG_NAME = "accessShowLogger";     // 访问日志

    private static Map<String, Logger> loggers = new HashMap<>();
    private static String DEF_APPNAME;

    @PostConstruct
    private void of(){
        DEF_APPNAME = this.APPNAME;
    }

    public static Logger getLogger(String logName) {
        loggers.computeIfAbsent(logName, LoggerFactory::getLogger);
        return loggers.get(logName);
    }

    private static Logger getLogger() {
        return getLogger(ACCESS_SHOW_LOG_NAME);
    }

    /**
     * 获取默认traceId
     * @return
     */
    public static String getTraceId(){
        try {
            String traceId = XContextUtil.getValue(DefaultDataEnums.ThreadLocalKeys.LOG_TRACE_ID.getKey(), "");

            if (StringUtils.isEmpty(traceId)){
                traceId = XContextUtil.getValue(DefaultDataEnums.ThreadLocalKeys.LOG_DEFAULT_TRACE_ID.getKey(), "");
            }

            if (StringUtils.isEmpty(traceId)){
                String mdcTraceId = MDC.get("traceId");
                if (!StringUtils.isEmpty(traceId)){
                    traceId = mdcTraceId;
                }else {
                    traceId = XBasicUtil.uuid();
                }
                XContextUtil.setValue(DefaultDataEnums.ThreadLocalKeys.LOG_DEFAULT_TRACE_ID.getKey(), traceId);
            }

            return traceId;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取默认spanId
     * @return
     */
    public static String getSpanId(){
        try {
            String spanId = XContextUtil.getValue(DefaultDataEnums.ThreadLocalKeys.LOG_SPAN_ID.getKey(),"");
            if(StringUtils.isEmpty(spanId)){
                //mdc中的spanId为准
//        String mdcSpanId = MDC.get("spanId");
//        if (!StringUtils.isEmpty(mdcSpanId)){
//          spanId = mdcSpanId;
//        }else {
//          spanId = RandomStringUtils.random(12, true, true);
//        }
                spanId = RandomStringUtils.random(12, true, true);
                XContextUtil.setValue(DefaultDataEnums.ThreadLocalKeys.LOG_SPAN_ID.getKey(),spanId);
            }
            return spanId;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取指定source的cspanId。
     * source不等于DefaultDataEnums.Source.THIRD_API时： cspanId和spanId一样
     * @return
     */
    public static String getChildSpanId(DefaultDataEnums.Source source){
        if (source != null && source.getStatus().equals(DefaultDataEnums.Source.THIRD_API.getStatus())){
            return getChildSpanId();
        }else {
            return getSpanId();
        }
    }

    /**
     * 获取默认cspanId
     * @return
     */
    public static String getChildSpanId(){
        try {
            String childSpanId = XContextUtil.getValue(DefaultDataEnums.ThreadLocalKeys.LOG_CHILD_SPAN_ID.getKey(), "");
            if (StringUtils.isEmpty(childSpanId)){
                childSpanId = "";
            }
            return childSpanId;
        } catch (Exception e) {
            return "";
        }
    }


    public static void traceIdOf(DefaultDataEnums.Source source){
        if (source != null){
            XContextUtil.init();
            String traceId;
            //mdc中的traceId为准
            String mdcTraceId = MDC.get("traceId");
            if (!StringUtils.isEmpty(mdcTraceId)){
                traceId = mdcTraceId;
            }else {
                traceId = XBasicUtil.uuid();
            }
            XContextUtil.setValue(DefaultDataEnums.ThreadLocalKeys.LOG_DEFAULT_TRACE_ID.getKey(), traceId);

            String spanId = MDC.get("spanId");
            if(StringUtils.isEmpty(spanId)){
                spanId=  RandomStringUtils.random(12, true, true);
            }
            //mdc中的spanId为准
//      String mdcSpanId = MDC.get("spanId");
//      if (!StringUtils.isEmpty(mdcSpanId)){
//        spanId = mdcSpanId;
//      }
            XContextUtil.setValue(DefaultDataEnums.ThreadLocalKeys.LOG_SPAN_ID.getKey(), spanId);
        }
    }

    public static void resetTraceIdOf(String defaultTraceId , String paramTraceId, String spanId, String childSpanId){
        if (!StringUtils.isEmpty(defaultTraceId)){
            XContextUtil.setValue(DefaultDataEnums.ThreadLocalKeys.LOG_DEFAULT_TRACE_ID.getKey(), defaultTraceId);
        }
        if (!StringUtils.isEmpty(paramTraceId)){
            XContextUtil.setValue(DefaultDataEnums.ThreadLocalKeys.LOG_TRACE_ID.getKey(), paramTraceId);
        }
        if (!StringUtils.isEmpty(spanId)){
            XContextUtil.setValue(DefaultDataEnums.ThreadLocalKeys.LOG_SPAN_ID.getKey(), spanId);
        }
        if (!StringUtils.isEmpty(childSpanId)){
            XContextUtil.setValue(DefaultDataEnums.ThreadLocalKeys.LOG_CHILD_SPAN_ID.getKey(), childSpanId);
        }
    }

    public static void monitorLogger(String logKey, String source, String action, String executionTime, String domain,
                                     String startDate, String endDate, String appId, String clientIp, Integer code,
                                     String message, DefaultDataEnums.Level level, String requestMethod){
        LogModel defaultLogModel = logModelInit(logKey, source, DefaultDataEnums.Type.SYSTEM.getStatus(),
                action, null, null, executionTime, requestMethod, domain, startDate, endDate,
                message, code,  appId, null, null, null, null,
                level, clientIp);
        invoke(MONITOR_LOG_NAME , defaultLogModel , null);
    }

    public static void accessShowLogger(String logKey, String source, String action, Object request, Object response,
                                        String executionTime, String requestMethod, String domain, String startDate,
                                        String endDate){
        LogModel defaultLogModel = logModelInit(logKey, source, DefaultDataEnums.Type.SYSTEM.getStatus(),
                action, request, response, executionTime, requestMethod, domain, startDate , endDate,null,
                null,null, null,null,null,null,
                null,null);
        invoke(ACCESS_SHOW_LOG_NAME , defaultLogModel , null);
    }

    public static void apiAccessShowLogger(String logKey, String source, String action, String appId, String appName,
                                           Object request, Object response, String executionTime, String requestMethod,
                                           String domain, String startDate, String endDate,
                                          String actionId, String actionName, String args, String clientIp){
        LogModel defaultLogModel = logModelInit(logKey, source, DefaultDataEnums.Type.SYSTEM.getStatus(),
                action, request, response, executionTime, requestMethod, domain, startDate, endDate,null,
                null, appId, appName,
                actionId, actionName, args, DefaultDataEnums.Level.INFO, clientIp);
        invoke(ACCESS_SHOW_LOG_NAME , defaultLogModel , null);
    }

    public static void apiProductErrorLogger(String logKey, String source, String action, String appId, String appName,
                                             Object request, Object response, String executionTime, String requestMethod,
                                             String domain, String startDate, String endDate,
                                             String actionId, String actionName, String message, Integer code){
        LogModel defaultLogModel = logModelInit(logKey, source, DefaultDataEnums.Type.SYSTEM.getStatus(),
                action, request, response, executionTime, requestMethod, domain, startDate, endDate,message,code,
                appId, appName,  actionId,
                actionName, null, DefaultDataEnums.Level.ERROR, null);
        invoke(ACCESS_SHOW_LOG_NAME , defaultLogModel , null);
    }

    public static void sysErrorLogger(String logKey, String source, String message, Integer code,  String action,
                                      Object request, Object response, String executionTime, String requestMethod,
                                      String domain){
        LogModel defaultLogModel = logModelInit(logKey, source, DefaultDataEnums.Type.SYSTEM.getStatus(), action, request,
                response, executionTime, requestMethod, domain , null, null,
                message, code, null,null,null, null,null,DefaultDataEnums.Level.ERROR,null);
        invoke(SYS_ERROR_LOG_NAME , defaultLogModel , null);
    }

    private static void invoke(String logType , LogModel defaultLogModel, Throwable throwable){
        Logger logger = StringUtils.isEmpty(logType) ? getLogger() : getLogger(logType);

        Object[] paramsArr = new Object[2];
        Class[] paramsTypeArr = new Class[2];

        String methodName = StringUtils.isEmpty(defaultLogModel.getLevel()) ?
                DefaultDataEnums.Level.INFO.getStatus() : defaultLogModel.getLevel();
        logJson(paramsArr , paramsTypeArr , defaultLogModel , throwable);
        try {
            Class loggerClass = logger.getClass();
            if (loggerClass != null){
                Method method = loggerClass.getDeclaredMethod(methodName, paramsTypeArr);
                method.invoke(logger, paramsArr);
            }
        } catch (Exception e) {
            log.error("系统日志记录异常 | logKey: {} | source: {} | traceId: {}" , defaultLogModel.getLogKey(),
                    defaultLogModel.getSource() , defaultLogModel.getTraceId() , e);
        }
    }

    private static void logStash(Object[] paramsArr , Class[] paramsTypeArr , LogModel defaultLogModel, Throwable throwable){
        if (defaultLogModel.getRequest() != null){
            defaultLogModel.setRequest(JacksonUtil.toJsonString(defaultLogModel.getRequest()));
        }
        if (defaultLogModel.getResponse() != null){
            defaultLogModel.setResponse(JacksonUtil.toJsonString(defaultLogModel.getResponse()));
        }
        Map<String , Object> defaultLogMap = XMapUtil.beanToMap(defaultLogModel);

        List<Object> argsList = Lists.newArrayList();
        StringBuilder paramsBuilder = new StringBuilder();
        for (Map.Entry<String , Object> logEntry: defaultLogMap.entrySet()){
            paramsBuilder.append(logEntry.getKey() + " : {} ,");
            argsList.add(logEntry.getValue());
        }
        if (throwable != null){
            argsList.add(throwable);
        }
        Object[] argsArr = new Object[argsList.size()];
        argsList.toArray(argsArr);

        paramsArr[0] = paramsBuilder.toString();
        paramsArr[1] = argsArr;

        paramsTypeArr[0] = String.class;
        paramsTypeArr[1] = argsArr.getClass();
    }

    private static void logJson(Object[] paramsArr , Class[] paramsTypeArr , LogModel defaultLogModel, Throwable throwable){
        if (defaultLogModel.getRequest() != null){
            String requestStr;
            if (!defaultLogModel.getRequest().getClass().equals(String.class)){
                requestStr = JacksonUtil.toJsonString(defaultLogModel.getRequest());
            }else {
                requestStr = defaultLogModel.getRequest().toString();
            }
            defaultLogModel.setRequest("'"+requestStr+"'");
        }
        if (defaultLogModel.getResponse() != null){
            String responseStr;
            if (!defaultLogModel.getResponse().getClass().equals(String.class)){
                responseStr = JacksonUtil.toJsonString(defaultLogModel.getResponse());
            }else {
                responseStr = defaultLogModel.getResponse().toString();
            }
            defaultLogModel.setResponse("'"+responseStr+"'");
        }

        paramsArr[0] = JacksonUtil.toJsonString(defaultLogModel);
        paramsTypeArr[0] = String.class;

        paramsTypeArr[1] = Throwable.class;
        if (throwable != null){
            paramsArr[1] = throwable;
        }else {
            paramsArr[1] = null;
        }
    }

    private static LogModel logModelInit(String logKey, String source, String type, String action,
                                         Object request, Object response, String executionTime,
                                         String requestMethod, String domain, String startDate,
                                         String endDate , String message , Integer code, String appId, String appName,
                                          String actionId, String actionName, String args, DefaultDataEnums.Level level, String clientIp){
        LogModel defaultLogModel = new LogModel();

        defaultLogModel.setApp(DEF_APPNAME);

        if (!StringUtils.isEmpty(logKey)){
            defaultLogModel.setLogKey(logKey);
        }
        if (!StringUtils.isEmpty(source)){
            defaultLogModel.setSource(source);
        }
        if (!StringUtils.isEmpty(type)){
            defaultLogModel.setType(type);
        }
        if (!StringUtils.isEmpty(action)){
            defaultLogModel.setAction(action);
        }
        if (!StringUtils.isEmpty(executionTime)){
            defaultLogModel.setRt(Long.valueOf(executionTime));
        }
        if (code!=null){
            defaultLogModel.setCode(code);
        }
        if (!StringUtils.isEmpty(message)){
            defaultLogModel.setMessage(message);
        }
        if (request != null){
            defaultLogModel.setRequest(request);
        }
        if (response != null){
            defaultLogModel.setResponse(response);
        }
        if (!StringUtils.isEmpty(requestMethod)){
            defaultLogModel.setRequestMethod(requestMethod);
        }
        if (!StringUtils.isEmpty(domain)){
            defaultLogModel.setDomain(domain);
        }
        if (!StringUtils.isEmpty(startDate)){
            defaultLogModel.setStartTime(startDate);
            defaultLogModel.setShowStartTime(XDateUtil.toStringDateByUtcDate(startDate,
                    XDateUtil.CTT_DATE_TIME_WITH_MILLISECOND_FORMATTER));
        }
        if (!StringUtils.isEmpty(endDate)){
            defaultLogModel.setEndTime(endDate);
            defaultLogModel.setShowEndTime(XDateUtil.toStringDateByUtcDate(endDate,
                    XDateUtil.CTT_DATE_TIME_WITH_MILLISECOND_FORMATTER));
        }
        if (!StringUtils.isEmpty(appId)){
            defaultLogModel.setAppId(appId);
        }
        if (!StringUtils.isEmpty(appName)){
            defaultLogModel.setAppName(appName);
        }

        if (!StringUtils.isEmpty(actionId)){
            defaultLogModel.setActionId(actionId);
        }
        if (!StringUtils.isEmpty(actionName)){
            defaultLogModel.setActionName(actionName);
        }
        if (!StringUtils.isEmpty(args)){
            defaultLogModel.setArgs(args);
        }
        if (level != null && !StringUtils.isEmpty(level.getStatus())){
            defaultLogModel.setLevel(level.getStatus());
        }
        if (!StringUtils.isEmpty(clientIp)){
            defaultLogModel.setClientIp(clientIp);
        }

        defaultLogModel.setTraceId(getTraceId());
        defaultLogModel.setSpanId(getSpanId());
        if (!StringUtils.isEmpty(source) && source.equals(DefaultDataEnums.Source.THIRD_API.getStatus())){
            defaultLogModel.setCspanId(getChildSpanId());
        }else {
            defaultLogModel.setCspanId("");
        }

        defaultLogModel.setServerIp(sysMachineName());
        return defaultLogModel;
    }

    private static String sysMachineName() {
        String r = XContextUtil.getValue(DefaultDataEnums.ThreadLocalKeys.LOG_SERVER_IP.getKey(), "");
        if (StringUtils.isEmpty(r)){
            try {
                InetAddress address = InetAddress.getLocalHost();
                r = address.getHostAddress();
            } catch (Exception e) {
                try {
                    r = System.getenv("HOSTNAME");
                } catch (Exception ee) {
                }
            }
            if (StringUtils.isEmpty(r)){
                r = "UnKnow-Address";
            }
            XContextUtil.setValue(DefaultDataEnums.ThreadLocalKeys.LOG_SERVER_IP.getKey(), r);
        }
        return r;
    }
}
