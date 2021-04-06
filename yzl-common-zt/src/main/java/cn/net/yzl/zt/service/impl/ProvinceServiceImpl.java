package cn.net.yzl.zt.service.impl;

import cn.net.yzl.common.util.JsonUtil;
import cn.net.yzl.zt.config.redis.RedisUtil;
import cn.net.yzl.zt.entity.Province;
import cn.net.yzl.zt.mapper.ProvinceMapper;
import cn.net.yzl.zt.model.constant.RedisConstant;
import cn.net.yzl.zt.service.ProvinceService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService {

    @Resource
    private ProvinceMapper provinceMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询省份列表
     * @param countryId 国家id
     * @param regionCode 大区编号
     * @return
     */
    @Override
    public List<Province> getProvinceList(Integer countryId,String regionCode) {
        log.info("查询省份列表countryId:{}，regionCode:{}", countryId,regionCode);
        List<Province> provinceList;
        String province;
        if(StringUtils.hasText(regionCode)){
            province = (String) redisUtil.hget(RedisConstant.PROVINCE_LIST, regionCode);
            if(StringUtils.hasText(province)){
                provinceList = JSONObject.parseArray(province, Province.class);
                log.info("查询缓存regionCodeProvince:{}", JsonUtil.toJsonStr(provinceList));
            }else {
                provinceList = provinceMapper.getProvinceListByRegionCode(regionCode);
                log.info("查询数据库regionCodeProvince:{}", JsonUtil.toJsonStr(provinceList));
                if(!CollectionUtils.isEmpty(provinceList)){
                    province = JSONObject.toJSONString(provinceList);
                    redisUtil.hset(RedisConstant.PROVINCE_LIST, regionCode, province, RedisConstant.SECOND_SEVEN_EXPIRE_TIME);//7天缓存
                }
            }
        }else {
            province = (String) redisUtil.hget(RedisConstant.PROVINCE_LIST, String.valueOf(countryId));
            if (StringUtils.hasText(province)) {
                provinceList = JSONObject.parseArray(province, Province.class);
                log.info("查询缓存countryIdProvince:{}", JsonUtil.toJsonStr(provinceList));
            } else {
                provinceList = provinceMapper.getProvinceListByCountryId(countryId);
                log.info("查询数据库countryIdProvince:{}", JsonUtil.toJsonStr(provinceList));
                if (!CollectionUtils.isEmpty(provinceList)) {
                    Collections.swap(provinceList, 0, 17);//将河北省放到第一位
                    province = JSONObject.toJSONString(provinceList);
                    redisUtil.hset(RedisConstant.PROVINCE_LIST, String.valueOf(countryId), province, RedisConstant.SECOND_SEVEN_EXPIRE_TIME);//7天缓存
                }

            }
        }
        return provinceList;
    }

    @Override
    public int createProvinceInfo(Province province) {
        return provinceMapper.insertSelective(province);
    }
}
