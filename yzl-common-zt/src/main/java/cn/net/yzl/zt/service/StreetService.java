package cn.net.yzl.zt.service;


import cn.net.yzl.zt.entity.Street;

import java.util.List;
import java.util.Map;

public interface StreetService {

    /**
     * 查询街道列表
     * @return
     */
    List<Street> getStreetList(Map<String, Object> map);

}
