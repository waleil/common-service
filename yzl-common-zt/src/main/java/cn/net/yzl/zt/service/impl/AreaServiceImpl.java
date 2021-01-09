package cn.net.yzl.zt.service.impl;

import cn.net.yzl.zt.entity.Area;
import cn.net.yzl.zt.mapper.AreaMapper;
import cn.net.yzl.zt.service.AreaService;
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
     * @param cityId 城市id
     * @return
     */
    @Override
    public List<Area> getAreaList(Integer cityId) {
        return areaMapper.getAreaList(cityId);
    }
}
