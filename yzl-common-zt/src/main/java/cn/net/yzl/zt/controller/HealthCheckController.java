package cn.net.yzl.zt.controller;

import cn.net.yzl.zt.config.redis.RedisUtil;
import cn.net.yzl.zt.model.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthCheck")
public class HealthCheckController {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 健康检查
     * @return
     */
    @GetMapping("/daoHealthCheck")
    public String healthCheck() {
        return "OK";
    }


    /**
     * 修复缓存
     * @return
     */
    @GetMapping("/repairRedisInfo")
    public String repairRedisInfo(@RequestParam String key,@RequestParam(required = false) String item) {
        if(StringUtils.hasText(item)) {
            String keys = (String) redisUtil.hget(key, item);
            if (StringUtils.hasText(keys)) {
                redisUtil.hdel(key,item);
            }
        }
        if(StringUtils.hasText(key)){
            String keys = (String) redisUtil.get(key);
            if(StringUtils.hasText(keys)) {
                redisUtil.del(key);
            }
        }
        return "OK";
    }
}
