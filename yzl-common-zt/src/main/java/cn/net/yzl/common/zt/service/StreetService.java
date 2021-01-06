package cn.net.yzl.common.zt.service;


import cn.net.yzl.common.zt.entity.Street;

import java.util.List;

public interface StreetService {

    /**
     * 查询街道列表
     * @return
     */
    List<Street> getStreetList();

}
