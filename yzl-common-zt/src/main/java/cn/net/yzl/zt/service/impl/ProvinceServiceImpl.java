package cn.net.yzl.zt.service.impl;

import cn.net.yzl.zt.entity.Province;
import cn.net.yzl.zt.mapper.ProvinceMapper;
import cn.net.yzl.zt.service.ProvinceService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService {

    @Resource
    private ProvinceMapper provinceMapper;

    /**
     * 查询省份列表
     * @param countryId 国家id
     * @param regionCode 大区编号
     * @return
     */
    @Override
    public List<Province> getProvinceList(Integer countryId,String regionCode) {
        List<Province> provinceList = null;
        if(StringUtils.hasText(regionCode)){
            provinceList = provinceMapper.getProvinceListByRegionCode(regionCode);
        }else{
            provinceList = provinceMapper.getProvinceListByCountryId(countryId);
        }

        if(!CollectionUtils.isEmpty(provinceList)){
            Collections.swap(provinceList,0,17);//将河北省放到第一位
        }
        return provinceList;
    }

    @Override
    public int createProvinceInfo(Province province) {
        return provinceMapper.insertSelective(province);
    }
}
