package cn.net.yzl.common.zt.mapper;

import cn.net.yzl.common.zt.entity.RegionProvince;

public interface RegionProvinceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RegionProvince record);

    int insertSelective(RegionProvince record);

    RegionProvince selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RegionProvince record);

    int updateByPrimaryKey(RegionProvince record);
}