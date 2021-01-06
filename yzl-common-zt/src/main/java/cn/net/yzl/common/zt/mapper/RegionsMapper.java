package cn.net.yzl.common.zt.mapper;

import cn.net.yzl.common.zt.entity.Regions;

public interface RegionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Regions record);

    int insertSelective(Regions record);

    Regions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Regions record);

    int updateByPrimaryKey(Regions record);
}