package cn.net.yzl.common.zt.service;


import cn.net.yzl.common.zt.entity.Regions;
import java.util.List;

public interface RegionsService {

    /**
     * 查询大区列表
     * @return
     */
    List<Regions> getRegionsList();

}
