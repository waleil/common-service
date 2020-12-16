package cn.net.yzl.common.service;

import cn.net.yzl.common.dao.entity.City;

import java.util.List;

public interface CityService {

    /**
     * 查询城市信息列表
     * @return
     */
    List<City> getCityList();

}
