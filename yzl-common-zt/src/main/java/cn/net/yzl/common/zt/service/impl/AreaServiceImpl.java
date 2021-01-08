package cn.net.yzl.common.zt.service.impl;

import cn.net.yzl.common.zt.entity.Area;
import cn.net.yzl.common.zt.mapper.AreaMapper;
import cn.net.yzl.common.zt.service.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("areaService")
public class AreaServiceImpl implements AreaService {

    @Resource
    private AreaMapper areaMapper;

    /**
     * 查询地区列表
     * @return
     */
    @Override
    public List<Area> getAreaList(Map<String,Object> map) {
        return areaMapper.getAreaList(map);
    }
}
