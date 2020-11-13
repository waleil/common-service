package cn.net.yzl.common.swagger2;


import cn.hutool.core.util.ReUtil;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;





@Configuration
@EnableSwagger2
@Profile({"dev","test"})
public class Swagger2 {

    private String packageName="cn.net.yzl.*.controller"; // 被扫描的包路径

   @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.basePackage(packageName))
                .apis(basePackage("^"+packageName+"$"))
                .paths(PathSelectors.any())
                .build();

    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot利用swagger构建java的api文档")
                .description("简单优雅的restfun风格")
                .termsOfServiceUrl("http://blog.csdn.net/forezp")
                .version("1.0")
                .contact(new Contact("pdl",null,null))
                .license("御芝林")
                .build();
    }

    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(
                "test-app-client-id",
                "test-app-client-secret",
                "test-app-realm",
                "test-app",
                "apiKey",
                ApiKeyVehicle.HEADER,
                "api_key",
                "," /*scope separator*/);
    }

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(
                "validatorUrl",// url
                "none",       // docExpansion          => none | list
                "alpha",      // apiSorter             => alpha
                "schema",     // defaultModelRendering => schema
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                false,        // enableJsonEditor      => true | false
                true,         // showRequestHeaders    => true | false
                60000L);      // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)
    }

    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage( final String basePackage) {
        return input -> {
            // 循环判断匹配
            String packageName = input.getPackage().getName();
            // cn.net.yzl.ab.controller
            //cn.net.yzl.xx.controller
            String regex=basePackage.replace("*","[a-zA-Z]+");
           return ReUtil.isMatch(regex,packageName);
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }


    public static void main(String[] args) {
        String basePackage ="^cn.net.yzl.*.controller$";
        String regex=basePackage.replace("*","[a-zA-Z]+");

        String a="cn.net.yzl.aaa.controller";
        System.err.println(ReUtil.isMatch(regex,a));

    }

}

