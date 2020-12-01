package cn.net.yzl.logger.model;

import cn.net.yzl.logger.common.XDateUtil;
import cn.net.yzl.logger.enums.DefaultDataEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title: LogModel
 * @Author lichanghong
 * @Date: 2020/11/30 9:38 上午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogModel {
    private static final long serialVersionUID = 1L;

    /**
     * 应用名称
     */
    private String app;
    /**
     * 请求链路traceID
     */
    private String traceId;
    /**
     * 请求链路spanID
     */
    private String spanId;
    /**
     * 请求链路childSpanID
     */
    private String cspanId;
    /**
     * 日志唯一标记
     */
    private String logKey = DefaultDataEnums.LOGKEY;
    /**
     * 执行时长
     */
    private Long rt;
    /**
     * 日志记录开始时间
     */
    private String startTime = XDateUtil.getCurrentUtcStringSDate(true);
    /**
     * 日志记录结束时间
     */
    private String endTime;
    /**
     * 日志记录开始展示时间
     */
    private String showStartTime;
    /**
     * 日志记录结束展示时间
     */
    private String showEndTime;
    /**
     * 日志等级  info、error、warning
     */
    private String level = DefaultDataEnums.Level.INFO.getStatus();
    /**
     * 日志来源  default、es、redis、scheduler、mq、db、call_api、provided_api
     */
    private String source = DefaultDataEnums.Source.DEFAULT.getStatus();
    /**
     * 执行动作  add、del、update、query
     */
    private String action;
    /**
     * 日志类型  system:系统或中台API服务  local:内部正常逻辑判断或代码异常
     */
    private String type = DefaultDataEnums.Type.LOCAL.getStatus();
    /**
     * 请求方式，一般API请求记录  GET POST
     */
    private String requestMethod;
    /**
     * 请求域名，一般API请求记录
     */
    private String domain;
    /**
     * 服务器IP
     */
    private String serverIp;
    /**
     * 客户端IP
     */
    private String clientIp;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 系统返回码
     */
    private Integer code = 0;
    /**
     * 业务返回码
     */
    private String bizCode = "0";
    /**
     * 请求参数
     */
    private Object request;
    /**
     * 返回数据
     */
    private Object response;

    /**
     * 对外接口关键入参数据统计-appId
     */
    private String appId;
    /**
     * 对外接口关键入参数据统计-统一网关AppId
     */
    private String appName;
    /**
     * 对外接口关键入参数据统计-actionId
     */
    private String actionId;
    /**
     * 对外接口关键入参数据统计-actionName
     */
    private String actionName;
    /**
     * 对外接口关键入参数统计-所有参数做一次拼接
     */
    private String args;
}
