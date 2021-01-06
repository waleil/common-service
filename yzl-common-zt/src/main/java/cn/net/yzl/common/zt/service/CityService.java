package cn.net.yzl.common.zt.service;


import cn.net.yzl.common.zt.entity.City;

import java.util.List;
import java.util.Map;

public interface CityService {

    /**
     * 查询城市列表
     * @return
     */
    List<City> getCityList(Map<String,Object> map);

}
