package cn.net.yzl.zt.mapper;


import cn.net.yzl.zt.entity.Province;

import java.util.List;
import java.util.Map;

public interface ProvinceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);

    /**
     * 根据大区编号查省份
     * @param regionCode
     * @return
     */
    List<Province> getProvinceListByRegionCode(String regionCode);

    /**
     * 根据国家id查省份
     * @param countryId
     * @return
     */
    List<Province> getProvinceListByCountryId(Integer countryId);


}