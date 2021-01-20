package cn.net.yzl.zt.service;

import cn.net.yzl.zt.entity.Nation;
import java.util.List;
import java.util.Map;

public interface NationService {

    /**
     * 获取民族列表
     * @param map
     * @return
     */
    List<Nation> getNationList(Map<String, Object> map);

}
