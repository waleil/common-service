package cn.net.yzl.common.zt.service.impl;

import cn.net.yzl.common.zt.entity.City;
import cn.net.yzl.common.zt.mapper.CityMapper;
import cn.net.yzl.common.zt.service.CityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cityService")
public class CityServiceImpl implements CityService {

    @Resource
    private CityMapper cityMapper;

    /**
     * 查询城市列表
     * @return
     */
    @Override
    public List<City> getCityList() {
        return cityMapper.getCityList();
    }
}
