package cn.net.yzl.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"cn.net.yzl.zt","cn.net.yzl.logger"})
@EnableTransactionManagement(order = 10) //开启事务，并设置order值，默认是Integer的最大值
@EnableDiscoveryClient
public class ZtApplication {


    public static void main(String[] args) {
        SpringApplication.run(ZtApplication.class, args);
    }

/*    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }

    *//**
     * 跨域过滤器
     *
     * @return CorsFilter
     *//*
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }*/

}
