package cn.net.yzl.common.zt.service.impl;

import cn.net.yzl.common.zt.entity.Regions;
import cn.net.yzl.common.zt.mapper.RegionsMapper;
import cn.net.yzl.common.zt.service.RegionsService;
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
