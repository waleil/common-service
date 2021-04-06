package cn.net.yzl.zt.service.impl;

import cn.net.yzl.common.util.JsonUtil;
import cn.net.yzl.zt.config.redis.RedisUtil;
import cn.net.yzl.zt.entity.Area;
import cn.net.yzl.zt.entity.Street;
import cn.net.yzl.zt.mapper.StreetMapper;
import cn.net.yzl.zt.model.constant.RedisConstant;
import cn.net.yzl.zt.service.StreetService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("streetService")
public class StreetServiceImpl implements StreetService {

    @Resource
    private StreetMapper streetMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询街道列表
     * @param areaId 地区id
     * @return
     */
    @Override
    public List<Street> getStreetList(Integer areaId) {
        log.info("查询街道列表areaId:{}", areaId);
        List<Street> streetList;
        String street = (String) redisUtil.hget(RedisConstant.STREET_LIST,String.valueOf(areaId));
        if(StringUtils.hasText(street)){
            streetList = JSONObject.parseArray(street, Street.class);
            log.info("查询缓存street:{}", JsonUtil.toJsonStr(streetList));
        }else{
            streetList = streetMapper.getStreetList(areaId);
            log.info("查询数据库street:{}", JsonUtil.toJsonStr(streetList));
            if(!CollectionUtils.isEmpty(streetList)) {
                street = JSONObject.toJSONString(streetList);
                redisUtil.hset(RedisConstant.STREET_LIST, String.valueOf(areaId), street, RedisConstant.SECOND_SEVEN_EXPIRE_TIME);//7天缓存
            }
        }
        return streetList;
    }
}
