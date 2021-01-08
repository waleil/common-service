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
     * 查询省份信息列表
     * @return
     */
    List<Province> getProvinceList(Map<String,Object> map);

}