package cn.net.yzl.common.zt.service;


import cn.net.yzl.common.zt.entity.MobileAttribution;

import java.util.List;

public interface MobileAttributionService {

    /**
     * 查询手机号归属地列表
     * @return
     */
    List<MobileAttribution> getMobileAttributionList();

}
