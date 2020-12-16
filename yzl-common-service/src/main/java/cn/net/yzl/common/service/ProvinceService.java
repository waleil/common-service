package cn.net.yzl.common.service;

import cn.net.yzl.common.dao.entity.Province;

import java.util.List;

public interface ProvinceService {

    /**
     * 查询省份信息列表
     * @return
     */
    List<Province> getProvinceList();

    int createProvinceInfo(Province province);

}
