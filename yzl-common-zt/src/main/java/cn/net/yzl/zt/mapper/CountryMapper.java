package cn.net.yzl.zt.mapper;


import cn.net.yzl.zt.entity.Country;

import java.util.List;

public interface CountryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Country record);

    int insertSelective(Country record);

    Country selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Country record);

    int updateByPrimaryKey(Country record);

    /**
     * 查询国家列表
     * @return
     */
    List<Country> getCountryList();
}