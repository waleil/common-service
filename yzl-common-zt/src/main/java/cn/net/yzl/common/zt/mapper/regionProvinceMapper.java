package cn.net.yzl.common.zt.mapper;

import cn.net.yzl.common.zt.entity.regionProvince;

public interface regionProvinceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(regionProvince record);

    int insertSelective(regionProvince record);

    regionProvince selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(regionProvince record);

    int updateByPrimaryKey(regionProvince record);
}