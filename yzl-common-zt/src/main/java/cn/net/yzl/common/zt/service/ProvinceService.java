package cn.net.yzl.common.zt.service;


import cn.net.yzl.common.zt.entity.Province;

import java.util.List;

public interface ProvinceService {

    /**
     * 查询省份列表
     * @return
     */
    List<Province> getProvinceList();

    int createProvinceInfo(Province province);

}
