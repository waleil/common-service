package cn.net.yzl.zt.service;


import cn.net.yzl.zt.entity.Province;

import java.util.List;
import java.util.Map;

public interface ProvinceService {

    /**
     * 查询省份列表
     * @param countryId 国家id
     * @param regionCode 大区编号
     * @return
     */
    List<Province> getProvinceList(Integer countryId,String regionCode);

    int createProvinceInfo(Province province);

}
