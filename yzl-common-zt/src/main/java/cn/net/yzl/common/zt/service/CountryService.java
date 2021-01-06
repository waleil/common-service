package cn.net.yzl.common.zt.service;


import cn.net.yzl.common.zt.entity.Country;

import java.util.List;

public interface CountryService {

    /**
     * 查询国家列表
     * @return
     */
    List<Country> getCountryList();

}
