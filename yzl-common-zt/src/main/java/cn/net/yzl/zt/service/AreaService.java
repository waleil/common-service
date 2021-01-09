package cn.net.yzl.zt.service;

import cn.net.yzl.zt.entity.Area;

import java.util.List;
import java.util.Map;

public interface AreaService {

    /**
     * 查询区域列表
     * @param cityId 城市id
     * @return
     */
    List<Area> getAreaList(Integer cityId);

}
