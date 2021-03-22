package cn.net.yzl.zt.service.impl;

import cn.net.yzl.common.util.JsonUtil;
import cn.net.yzl.zt.config.redis.RedisUtil;
import cn.net.yzl.zt.entity.City;
import cn.net.yzl.zt.mapper.CityMapper;
import cn.net.yzl.zt.model.constant.RedisConstant;
import cn.net.yzl.zt.service.CityService;
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
@Service("cityService")
public class CityServiceImpl implements CityService {

    @Resource
    private CityMapper cityMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询城市列表
     * @param provinceId 省份id
     * @return
     */
    @Override
    public List<City> getCityList(Integer provinceId) {
        List<City> cityList;
        String city = (String) redisUtil.hget(RedisConstant.CITY_LIST,String.valueOf(provinceId));
        if(StringUtils.hasText(city)){
            cityList = JSONObject.parseArray(city, City.class);
            log.info("查询缓存city:{}", JsonUtil.toJsonStr(cityList));
        }else{
            cityList = cityMapper.getCityList(provinceId);
            log.info("查询数据库city:{}", JsonUtil.toJsonStr(cityList));
            if(!CollectionUtils.isEmpty(cityList)) {
                city = JSONObject.toJSONString(cityList);
                redisUtil.hset(RedisConstant.CITY_LIST, String.valueOf(provinceId), city, RedisConstant.SECOND_SEVEN_EXPIRE_TIME);//7天缓存
            }
        }
        return cityList;
    }
}
