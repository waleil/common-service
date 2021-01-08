package cn.net.yzl.zt.mapper;

import cn.net.yzl.zt.entity.RegionProvince;

import java.util.List;

public interface RegionProvinceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RegionProvince record);

    int insertSelective(RegionProvince record);

    RegionProvince selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RegionProvince record);

    int updateByPrimaryKey(RegionProvince record);

    /**
     * 查询大区省列表
     * @return
     */
    List<RegionProvince> getRegionProvinceList();
}