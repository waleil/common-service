package cn.net.yzl.zt.service.impl;

import cn.net.yzl.common.util.JsonUtil;
import cn.net.yzl.zt.config.redis.RedisUtil;
import cn.net.yzl.zt.entity.Nation;
import cn.net.yzl.zt.entity.Street;
import cn.net.yzl.zt.mapper.NationMapper;
import cn.net.yzl.zt.model.constant.RedisConstant;
import cn.net.yzl.zt.service.NationService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("nationService")
public class NationServiceImpl implements NationService {

    @Resource
    private NationMapper nationMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取民族列表
     * @param map
     * @return
     */
    @Override
    public List<Nation> getNationList(Map<String, Object> map) {
        log.info("获取民族列表nationMap:{}", map);
        List<Nation> nationList;
        String nation = (String) redisUtil.hget(RedisConstant.NATION_LIST,String.valueOf(map));
        if(StringUtils.hasText(nation)){
            nationList = JSONObject.parseArray(nation, Nation.class);
            log.info("查询缓存nation:{}", JsonUtil.toJsonStr(nationList));
        }else{
            nationList = nationMapper.getNationList(map);
            log.info("查询数据库nation:{}", JsonUtil.toJsonStr(nationList));
            if(!CollectionUtils.isEmpty(nationList)) {
                nation = JSONObject.toJSONString(nationList);
                redisUtil.hset(RedisConstant.NATION_LIST, String.valueOf(map), nation, RedisConstant.SECOND_SEVEN_EXPIRE_TIME);//7天缓存
            }
        }
        return nationList;
    }
}
