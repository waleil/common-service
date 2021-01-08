package cn.net.yzl.zt.mapper;


import cn.net.yzl.zt.entity.Area;

import java.util.List;
import java.util.Map;

public interface AreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    /**
     * 查询地区列表
     * @param map
     * @return
     */
    List<Area> getAreaList(Map<String, Object> map);
}