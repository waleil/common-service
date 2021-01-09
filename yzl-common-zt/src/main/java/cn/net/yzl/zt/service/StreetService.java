package cn.net.yzl.zt.service;

import cn.net.yzl.zt.entity.Street;
import java.util.List;

public interface StreetService {

    /**
     * 查询街道列表
     * @param areaId 地区id
     * @return
     */
    List<Street> getStreetList(Integer areaId);

}
