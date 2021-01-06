package cn.net.yzl.common.zt.service.impl;

import cn.net.yzl.common.zt.entity.MobileAttribution;
import cn.net.yzl.common.zt.mapper.MobileAttributionMapper;
import cn.net.yzl.common.zt.service.MobileAttributionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("mobileAttributionService")
public class MobileAttributionServiceImpl implements MobileAttributionService {

    @Resource
    private MobileAttributionMapper mobileAttributionMapper;

    /**
     * 查询手机号归属地列表
     * @return
     */
    @Override
    public List<MobileAttribution> getMobileAttributionList() {
        return mobileAttributionMapper.getMobileAttributionList();
    }
}
