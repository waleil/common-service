package cn.net.yzl.zt.service.impl;

import cn.net.yzl.common.util.JsonUtil;
import cn.net.yzl.zt.config.redis.RedisUtil;
import cn.net.yzl.zt.entity.Province;
import cn.net.yzl.zt.mapper.ProvinceMapper;
import cn.net.yzl.zt.service.ProvinceService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        List<Province> provinceList = new ArrayList<>();
        String province = (String) redisUtil.get("province");
        if(StringUtils.hasText(province)){
            provinceList = JSONObject.parseArray(province,Province.class);
            log.info("查询缓存province:{}", JsonUtil.toJsonStr(provinceList));
        }else{
            if(StringUtils.hasText(regionCode)){
                provinceList = provinceMapper.getProvinceListByRegionCode(regionCode);
            }else{
                provinceList = provinceMapper.getProvinceListByCountryId(countryId);
                if(!CollectionUtils.isEmpty(provinceList)){
                    Collections.swap(provinceList,0,17);//将河北省放到第一位
                }
            }
            String prov = JSONObject.toJSONString(provinceList);
            redisUtil.set("province",prov,60*60*24);
        }
        return provinceList;
    }

    @Override
    public int createProvinceInfo(Province province) {
        return provinceMapper.insertSelective(province);
    }
}
