package cn.net.yzl.operatelogger.annotate;


import cn.net.yzl.operatelogger.enums.BusinessType;

import java.lang.annotation.*;

/**
 * @ClassName OperateLog
 * @Descripion 用户行为日志注解需要注解在Controller类方法下
 * @Author liwenjie
 * @Date 2020/12/10 9:57
 **/
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog
{
    /**
     * 模块
     * 建议 如果为 基础模块:字典:增加
     */
    public String operModule() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;
}
