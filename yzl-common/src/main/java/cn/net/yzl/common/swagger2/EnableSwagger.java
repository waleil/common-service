package cn.net.yzl.common.swagger2;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({Swagger2.class})
public @interface EnableSwagger {
}
