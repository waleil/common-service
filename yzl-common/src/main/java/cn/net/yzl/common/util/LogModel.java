package cn.net.yzl.common.util;

import lombok.Data;
import org.slf4j.MDC;

@Data
public class LogModel {

    String type;
    /**
     * 注解 @SysLog 设置的 value
     */
    String apiName;

    String traceId = MDC.get("traceId");

    String apiUrl;

    Long wasteTime;

    String request;

    String response;

    String header;

    Integer errorCode;

    String errorMsg;

    String stackTrace;

    String param;

    /**
     * 接口调用结果
     */
    String status;

    /**
     * 相应码
     */
    String code;

    /**
     * 响应信息
     */
    String msg;
}
