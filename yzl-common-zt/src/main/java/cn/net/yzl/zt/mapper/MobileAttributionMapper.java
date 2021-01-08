package cn.net.yzl.zt.mapper;


import cn.net.yzl.zt.entity.MobileAttribution;

import java.util.List;

public interface MobileAttributionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileAttribution record);

    int insertSelective(MobileAttribution record);

    MobileAttribution selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileAttribution record);

    int updateByPrimaryKey(MobileAttribution record);

    /**
     * 查询手机号归属地列表
     * @return
     */
    List<MobileAttribution> getMobileAttributionList();
}