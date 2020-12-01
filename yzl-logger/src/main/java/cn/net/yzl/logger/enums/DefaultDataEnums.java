package cn.net.yzl.logger.enums;

import lombok.Getter;

/**
 * @title: DefaultDataEnums
 * @Author lichanghong
 * @Date: 2020/11/30 8:58 上午
 * @Version 1.0
 */
public class DefaultDataEnums {
    public static final String LOGKEY = "default-log-key";

    /**
     * ThreadLocal key
     */
    @Getter
    public enum ThreadLocalKeys {
        LOG_DEFAULT_TRACE_ID("defaultLogTraceId" , "默认traceId"),
        LOG_TRACE_ID("logTraceId", "参数传递traceId"),
        LOG_SPAN_ID("logSpanId", "spanId"),
        LOG_CHILD_SPAN_ID("logChildSpanId", "子spanId"),
        LOG_REQUEST_PARAMS("logRequestParams", "requestParams"),
        LOG_SERVER_IP("logServerIp", "logServerIp");

        private String key;
        private String desc;

        ThreadLocalKeys(String status, String desc) {
            this.key = status;
            this.desc = desc;
        }
    }


    /**
     * 日志等级
     */
    @Getter
    public enum Level {
        INFO("info" , "info"),
        ERROR("error", "error"),
        WARNING("warning", "warning"),
        DEBUG("debug", "warning");

        private String status;
        private String desc;

        Level(String status, String desc) {
            this.status = status;
            this.desc = desc;
        }
    }

    /**
     * 日志来源
     */
    @Getter
    public enum Source {
        DEFAULT("DEFAULT" , "default"),
        SCHEDULER("SCHEDULER" , "scheduler"),
        ES("ES", "es"),
        REDIS("REDIS" , "redis"),
        MEMORY_CACHE("MEMORY_CACHE" , "memory cache"),
        MQ("MQ", "rabbitMq"),
        KAFKA("KAFKA", "kafka"),
        DB("DB" , "db"),
        THIRD_API("THIRD_API", "调用依赖的API"),
        OUT_API("OUT_API", "对外提供的API");

        private String status;
        private String desc;

        Source(String status, String desc) {
            this.status = status;
            this.desc = desc;
        }
    }

    /**
     * 执行动作
     */
    @Getter
    public enum Action {
        CONNECT("connect", "connect"),
        ADD("set" , "add"),
        DEL("del", "del"),
        UPDATE("update", "update"),
        QUERY("query" , "get"),
        EXISTS("exists" , "Exists"),
        COUNT("count" , "count"),
        SEND("send", "send message"),
        CONSUMER("consumer" , "consumer message"),
        SYNC("SYNC", "enroll sync"),
        CHECK("CHECK" , "enroll check");

        private String status;
        private String desc;

        Action(String status, String desc) {
            this.status = status;
            this.desc = desc;
        }
    }

    /**
     * 日志类型
     */
    @Getter
    public enum Type {
        SYSTEM("system" , "系统或中台API服务"),
        LOCAL("local", "内部正常逻辑判断或代码异常");

        private String status;
        private String desc;

        Type(String status, String desc) {
            this.status = status;
            this.desc = desc;
        }
    }
}
