package cn.net.yzl.common.zt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthCheck")
public class HealthCheckController {

    /**
     * 健康检查
     * @return
     */
    @GetMapping("/daoHealthCheck")
    public String healthCheck() {
        return "OK";
    }

}
