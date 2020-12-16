package cn.net.yzl.common.zt.controller;

import cn.net.yzl.common.zt.api.TestApi;
import cn.net.yzl.common.zt.config.annotation.ReadDataSource;
import cn.net.yzl.common.zt.config.redis.RedisUtil;
import cn.net.yzl.common.entity.ComResponse;
import io.swagger.annotations.Api;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Api(value="测试",tags={"测试模块"})
public class TestController implements TestApi {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    RedissonClient redisson;


    @Override
    @GetMapping( "/testceshi")
    @ReadDataSource
    public ComResponse testceshi(@RequestParam String ceshi) {
        return ComResponse.success(ceshi);

    }
}
