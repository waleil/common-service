package cn.net.yzl.common.dao.mapper;

import cn.net.yzl.common.dao.entity.MobileAttribution;

public interface MobileAttributionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileAttribution record);

    int insertSelective(MobileAttribution record);

    MobileAttribution selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileAttribution record);

    int updateByPrimaryKey(MobileAttribution record);
}