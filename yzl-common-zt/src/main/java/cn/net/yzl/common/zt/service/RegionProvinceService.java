package cn.net.yzl.common.zt.service;

import cn.net.yzl.common.zt.entity.RegionProvince;

import java.util.List;

public interface RegionProvinceService {

    /**
     * 查询大区省列表
     * @return
     */
    List<RegionProvince> getRegionProvinceList();

}
