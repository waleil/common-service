package cn.net.yzl.common.service.impl;

import cn.net.yzl.common.dao.entity.City;
import cn.net.yzl.common.dao.mapper.CityMapper;
import cn.net.yzl.common.service.CityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cityService")
public class CityServiceImpl implements CityService {

    @Resource
    private CityMapper cityMapper;

    @Override
    public List<City> getCityList() {
        return cityMapper.getCityList();
    }
}
