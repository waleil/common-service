package cn.net.yzl.zt.service;


import cn.net.yzl.zt.entity.Regions;
import java.util.List;

public interface RegionsService {

    /**
     * 查询大区列表
     * @return
     */
    List<Regions> getRegionsList();

}
