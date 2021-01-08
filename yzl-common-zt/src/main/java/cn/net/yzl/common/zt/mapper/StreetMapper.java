package cn.net.yzl.common.zt.mapper;


import cn.net.yzl.common.zt.entity.Street;

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
     * @return
     */
    List<Street> getStreetList(Map<String, Object> map);

}