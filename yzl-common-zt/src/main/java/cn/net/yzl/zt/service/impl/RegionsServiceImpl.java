package cn.net.yzl.zt.service.impl;

import cn.net.yzl.zt.entity.Regions;
import cn.net.yzl.zt.mapper.RegionsMapper;
import cn.net.yzl.zt.service.RegionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("regionsService")
public class RegionsServiceImpl implements RegionsService {

    @Resource
    private RegionsMapper regionsMapper;

    /**
     * 查询大区列表
     * @return
     */
    @Override
    public List<Regions> getRegionsList() {
        return regionsMapper.getRegionsList();
    }
}
