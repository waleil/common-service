package cn.net.yzl.zt.mapper;


import cn.net.yzl.zt.entity.Street;

import java.util.List;
import java.util.Map;

public interface StreetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Street record);

    int insertSelective(Street record);

    Street selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);

    /**
     * 查询街道列表
     * @param areaId 地区id
     * @return
     */
    List<Street> getStreetList(Integer areaId);

}