package cn.net.yzl.zt.service;

import cn.net.yzl.zt.entity.RegionProvince;

import java.util.List;

public interface RegionProvinceService {

    /**
     * 查询大区省列表
     * @return
     */
    List<RegionProvince> getRegionProvinceList();

}
