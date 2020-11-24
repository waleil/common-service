package cn.net.yzl.common.util;

import cn.net.yzl.common.entity.LogModel;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 日志工具类
 *
 **/
@Slf4j
public class YLoggerUtil {

    /**
     * info日志
     *
     * @param functionName
     * @param content
     * @return
     */
    public static void infoLog(String functionName, String content) {

        LogModel logModel = new LogModel();
        logModel.setType("info");
        logModel.setParam(content);
        logModel.setApiName(functionName);
        log.info(JacksonUtil.toJsonString(logModel));
    }

    public static void accessLog(String status, String code, String message, String type, String apiName,
                                 String url, Long mills, String request, String header, String response) {
        LogModel logModel = new LogModel();
        logModel.setStatus(status);
        logModel.setCode(code);
        logModel.setMsg(message);
        setCommModel(type, apiName, url, mills, request, header, response, logModel);
    }

    private static void setCommModel(String type, String apiName, String url, Long mills, String request, String header, String response, LogModel logModel) {
        logModel.setType(type);
        logModel.setApiUrl(url);
        logModel.setWasteTime(mills);
        logModel.setApiName(apiName);
        logModel.setHeader(header);
        logModel.setRequest("'" + request + "'");
        logModel.setResponse("'" + response + "'");
        log.info(JacksonUtil.toJsonString(logModel));
    }

    public static void accessLog(String type, String apiName, String url, Long mills, String request, String response) {
        accessLog(type, apiName, url, mills, request, null, response);
    }

    /**
     * 访问日志
     *
     * @param apiName
     * @param mills
     * @param request
     * @param response
     * @return
     */
    public static void accessLog(String type, String apiName, String url, Long mills, String request, String header, String response) {

        LogModel logModel = new LogModel();
        setCommModel(type, apiName, url, mills, request, header, response, logModel);
    }

    /**
     * 系统异常日志
     *
     * @param errorCode
     * @param errorMsg
     * @param param
     * @param e
     * @return
     */
    public static void errorLog(String type, String apiName, Integer errorCode, String errorMsg, String param, Exception e) {

        LogModel logModel = new LogModel();
        logModel.setType(type);
        logModel.setErrorCode(errorCode);
        logModel.setErrorMsg(errorMsg);
        logModel.setStackTrace(toStackTrace(e, null));
        logModel.setApiName(apiName);
        logModel.setParam("'" + param + "'");
        log.error(JacksonUtil.toJsonString(logModel), e);
    }

    /**
     * 系统callout异常日志，打印callout的url
     * @param type
     * @param apiUrl
     * @param name
     * @param errorCode
     * @param errorMsg
     * @param param
     * @param e
     */
    public static void errorLog(String type, String apiUrl, String name, Integer errorCode, String errorMsg, String param, Exception e) {

        LogModel logModel = new LogModel();
        logModel.setType(type);
        logModel.setErrorCode(errorCode);
        logModel.setErrorMsg(errorMsg);
        logModel.setStackTrace(toStackTrace(e, null));
        logModel.setApiUrl(apiUrl);
        logModel.setApiName(name);
        logModel.setParam("'" + param + "'");
        log.error(JacksonUtil.toJsonString(logModel), e);
    }


    public static boolean isJson(String param) {
        try {
            JacksonUtil.parse(param);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isNumeric(String str) {
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String toStackTrace(Exception e, Integer length) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            e.printStackTrace(pw);
            if (length != null) {
                if (sw.toString().length() > length) {
                    return sw.toString().substring(0, 100);
                } else {
                    return sw.toString();
                }
            } else {
                return sw.toString();
            }
        } catch (Exception e1) {
            return "";
        }
    }

    public static Map stringToJson(String content) {
        try {
            content = content.replaceAll("&", " ");
            Map map = Splitter.on(" ").withKeyValueSeparator("=").split(content);
            return map;
        } catch (Exception e) {
            log.error("日志转化异常:" + toStackTrace(e, null));
            return null;
        }

    }


    /**
     * 示例
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        //XLoggerUtil.infoLog("创建合同签署文件(第一步)开始, 入参contractTemplateSignDTO=",  JacksonUtil.toJsonString(contractTemplateSignDTO));
        //XLoggerUtil.accessLog("api", "接口调用POST", url, time, requestParam, headerContent, res);
        //XLoggerUtil.errorLog("exception", url, "调用外部接口异常", ResponseCodeEnums.API_ERROR_CODE.getCode(), "http 'doGet' close CloseableHttpResponse exception", queryStr, e);

    }
}
