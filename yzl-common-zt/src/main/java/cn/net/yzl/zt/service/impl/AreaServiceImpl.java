package cn.net.yzl.zt.service.impl;

import cn.net.yzl.common.util.JsonUtil;
import cn.net.yzl.zt.config.redis.RedisUtil;
import cn.net.yzl.zt.entity.Area;
import cn.net.yzl.zt.mapper.AreaMapper;
import cn.net.yzl.zt.model.constant.RedisConstant;
import cn.net.yzl.zt.service.AreaService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
        log.info("查询地区列表cityId:{}", cityId);
        List<Area> areaList;
        String area = (String) redisUtil.hget(RedisConstant.AREA_LIST,String.valueOf(cityId));
        if(StringUtils.hasText(area)){
            areaList = JSONObject.parseArray(area, Area.class);
            log.info("查询缓存area:{}", JsonUtil.toJsonStr(areaList));
        }else{
            areaList = areaMapper.getAreaList(cityId);
            log.info("查询数据库area:{}", JsonUtil.toJsonStr(areaList));
            if(!CollectionUtils.isEmpty(areaList)) {
                area = JSONObject.toJSONString(areaList);
                redisUtil.hset(RedisConstant.AREA_LIST, String.valueOf(cityId), area, RedisConstant.SECOND_SEVEN_EXPIRE_TIME);//7天缓存
            }
        }
        return areaList;
    }
}
