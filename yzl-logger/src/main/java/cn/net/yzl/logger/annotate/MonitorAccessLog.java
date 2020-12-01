package cn.net.yzl.logger.annotate;

import cn.net.yzl.logger.enums.DefaultDataEnums;

import java.lang.annotation.*;

/**
 * @title: MonitorAccessLog
 * @Author lichanghong
 * @Date: 2020/11/30 8:56 上午
 * @Version 1.0
 */
@Documented
@Target(value={ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MonitorAccessLog {
    /**
     * 自定义logKey，不为空以此为准
     */
    String logKey() default "";
    /**
     * 从入参获取logKey，设置多个入参，将分别取入参值，并以"-"拼接
     */
    String[] logKeyParamName() default { };
    /**
     * 传递traceId的入参，默认为空，即取系统默认的traceId
     */
    String traceIdParam() default "";
    /**
     * 记录执行动作。默认query。 具体参考 DefaultDataEnums.Action
     */
    DefaultDataEnums.Action action() default DefaultDataEnums.Action.QUERY;
    /**
     * 记录日志来源。必传。具体参考 DefaultDataEnums.Source
     */
    DefaultDataEnums.Source source();

}
