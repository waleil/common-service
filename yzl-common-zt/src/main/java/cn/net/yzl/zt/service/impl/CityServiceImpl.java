package cn.net.yzl.zt.service.impl;

import cn.net.yzl.zt.entity.City;
import cn.net.yzl.zt.mapper.CityMapper;
import cn.net.yzl.zt.service.CityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("cityService")
public class CityServiceImpl implements CityService {

    @Resource
    private CityMapper cityMapper;

    /**
     * 查询城市列表
     * @return
     */
    @Override
    public List<City> getCityList(Map<String,Object> map) {
        return cityMapper.getCityList(map);
    }
}
