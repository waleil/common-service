package cn.net.yzl.common.dao.mapper;

import cn.net.yzl.common.dao.entity.Attribution;

public interface AttributionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Attribution record);

    int insertSelective(Attribution record);

    Attribution selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attribution record);

    int updateByPrimaryKey(Attribution record);
}