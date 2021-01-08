package cn.net.yzl.zt.service;


import cn.net.yzl.zt.entity.Province;

import java.util.List;
import java.util.Map;

public interface ProvinceService {

    /**
     * 查询省份列表
     * @return
     */
    List<Province> getProvinceList(Map<String,Object> map);

    int createProvinceInfo(Province province);

}