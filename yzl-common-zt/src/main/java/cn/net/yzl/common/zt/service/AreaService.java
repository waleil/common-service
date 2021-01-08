package cn.net.yzl.common.zt.service;

import cn.net.yzl.common.zt.entity.Area;

import java.util.List;
import java.util.Map;

public interface AreaService {

    /**
     * 查询区域列表
     * @return
     */
    List<Area> getAreaList(Map<String,Object> map);

}
