package cn.net.yzl.zt.service;


import cn.net.yzl.zt.entity.Country;

import java.util.List;

public interface CountryService {

    /**
     * 查询国家列表
     * @return
     */
    List<Country> getCountryList();

}
