package cn.net.yzl.zt.service.impl;

import cn.net.yzl.common.util.JsonUtil;
import cn.net.yzl.zt.config.redis.RedisUtil;
import cn.net.yzl.zt.entity.Area;
import cn.net.yzl.zt.mapper.AreaMapper;
import cn.net.yzl.zt.service.AreaService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("areaService")
public class AreaServiceImpl implements AreaService {

    @Resource
    private AreaMapper areaMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询地区列表
     * @param cityId 城市id
     * @return
     */
    @Override
    public List<Area> getAreaList(Integer cityId) {
        List<Area> areaList = new ArrayList<>();
        String area = (String) redisUtil.hget("area",String.valueOf(cityId));
        if(StringUtils.hasText(area)){
            areaList = JSONObject.parseArray(area, Area.class);
            log.info("查询缓存area:{}", JsonUtil.toJsonStr(areaList));
        }else{
            areaList = areaMapper.getAreaList(cityId);
            String are = JSONObject.toJSONString(areaList);
            redisUtil.hset("area",String.valueOf(cityId),are,60*60*24);
        }
        return areaList;
    }
}
