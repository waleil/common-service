package cn.net.yzl.zt.service.impl;

import cn.net.yzl.zt.entity.RegionProvince;
import cn.net.yzl.zt.mapper.RegionProvinceMapper;
import cn.net.yzl.zt.service.RegionProvinceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("regionProvinceService")
public class RegionProvinceServiceImpl implements RegionProvinceService {

    @Resource
    private RegionProvinceMapper regionProvinceMapper;

    /**
     * 查询大区省列表
     * @return
     */
    @Override
    public List<RegionProvince> getRegionProvinceList() {
        return regionProvinceMapper.getRegionProvinceList();
    }
}
