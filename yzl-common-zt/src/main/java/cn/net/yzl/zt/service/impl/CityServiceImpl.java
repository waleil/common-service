package cn.net.yzl.zt.service.impl;

import cn.net.yzl.zt.entity.City;
import cn.net.yzl.zt.mapper.CityMapper;
import cn.net.yzl.zt.service.CityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cityService")
public class CityServiceImpl implements CityService {

    @Resource
    private CityMapper cityMapper;

    /**
     * 查询城市列表
     * @param provinceId 省份id
     * @return
     */
    @Override
    public List<City> getCityList(Integer provinceId) {
        return cityMapper.getCityList(provinceId);
    }
}
