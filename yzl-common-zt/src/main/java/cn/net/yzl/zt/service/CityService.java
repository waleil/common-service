package cn.net.yzl.zt.service;


import cn.net.yzl.zt.entity.City;

import java.util.List;
import java.util.Map;

public interface CityService {

    /**
     * 查询城市列表
     * @param provinceId 省份id
     * @return
     */
    List<City> getCityList(Integer provinceId);

}
