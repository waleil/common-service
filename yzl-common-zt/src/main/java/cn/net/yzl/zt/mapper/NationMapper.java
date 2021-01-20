package cn.net.yzl.zt.mapper;

import cn.net.yzl.zt.entity.City;
import cn.net.yzl.zt.entity.Nation;

import java.util.List;
import java.util.Map;

public interface NationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Nation record);

    int insertSelective(Nation record);

    Nation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Nation record);

    int updateByPrimaryKey(Nation record);


    /**
     * 获取民族列表
     * @param map
     * @return
     */
    List<Nation> getNationList(Map<String, Object> map);
}