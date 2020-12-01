package cn.net.yzl.logger.annotate;

import cn.net.yzl.logger.enums.DefaultDataEnums;

import java.lang.annotation.*;

/**
 * @title: 定义系统日志注解
 * @Author lichanghong
 * @Date: 2020/11/30 8:59 上午
 * @Version 1.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SysAccessLog {
    /**
     * 自定义logKey，不为空以此为准
     */
    String logKey() default "";
    /**
     * 从入参获取logKey，设置多个入参，将分别取入参值，并以"-"拼接
     */
    String[] logKeyParamName() default { };
    /**
     * 记录日志来源。必传。具体参考 DefaultDataEnums.Source
     */
    DefaultDataEnums.Source source();
    /**
     * 记录执行动作。默认query。 具体参考 DefaultDataEnums.Action
     */
    DefaultDataEnums.Action action() default DefaultDataEnums.Action.QUERY;
    /**
     * 排除掉的入参参数，默认为空，即入参全部收集
     */
    String[] requestExcludeParamsName() default { };
    /**
     * 传递traceId的入参，默认为空，即取系统默认的traceId
     */
    String traceIdParam() default "";
    /**
     * API请求方式。ps: GET POST
     */
    String requestMetod() default "";
    /**
     * 是否记录开始日志。可以用来收集运行中和运行结束的日志。默认false，即不记录开始日志
     */
    boolean isRecodeStartLog() default false;
    /**
     * 是否记录返回值。默认true，即默认记录返回值
     */
    boolean isRecodeResponse() default true;
}
