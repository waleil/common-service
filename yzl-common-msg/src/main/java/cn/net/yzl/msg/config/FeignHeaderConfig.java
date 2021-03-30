package cn.net.yzl.msg.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/3/17 9:54
 * @title: FeignHeaderConfig
 * @description:
 */
@Configuration
public class FeignHeaderConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("appId", "msg");
        };
    }

}
