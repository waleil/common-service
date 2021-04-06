package cn.net.yzl.zt.service.impl;

import cn.net.yzl.common.util.JsonUtil;
import cn.net.yzl.zt.config.redis.RedisUtil;
import cn.net.yzl.zt.entity.Country;
import cn.net.yzl.zt.mapper.CountryMapper;
import cn.net.yzl.zt.model.constant.RedisConstant;
import cn.net.yzl.zt.service.CountryService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
@Slf4j
@Service("countryService")
public class CountryServiceImpl implements CountryService {

    @Resource
    private CountryMapper countryMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询国家列表
     * @return
     */
    @Override
    public List<Country> getCountryList() {
        List<Country> countryList;
        String country = (String) redisUtil.get(RedisConstant.COUNTRY_LIST);
        if(StringUtils.hasText(country)) {
            countryList = JSONObject.parseArray(country, Country.class);
            log.info("查询缓存country:{}", JsonUtil.toJsonStr(countryList));
        }else {
            countryList = countryMapper.getCountryList();
            log.info("查询数据库country:{}", JsonUtil.toJsonStr(countryList));
            if(!CollectionUtils.isEmpty(countryList)){
                country = JSONObject.toJSONString(countryList);
                redisUtil.set(RedisConstant.COUNTRY_LIST, country, RedisConstant.SECOND_SEVEN_EXPIRE_TIME);//7天缓存
            }
        }
        return countryList;
    }
}
