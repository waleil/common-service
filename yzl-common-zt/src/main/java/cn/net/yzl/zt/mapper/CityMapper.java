package cn.net.yzl.zt.mapper;


import cn.net.yzl.zt.entity.City;

import java.util.List;
import java.util.Map;

public interface CityMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);

    /**
     * 获取城市列表
     * @return
     */
    List<City> getCityList(Map<String,Object> map);
}