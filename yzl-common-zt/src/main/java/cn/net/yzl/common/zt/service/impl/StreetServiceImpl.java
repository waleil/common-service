package cn.net.yzl.common.zt.service.impl;

import cn.net.yzl.common.zt.entity.Street;
import cn.net.yzl.common.zt.mapper.StreetMapper;
import cn.net.yzl.common.zt.service.StreetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("streetService")
public class StreetServiceImpl implements StreetService {

    @Resource
    private StreetMapper streetMapper;

    /**
     * 查询街道列表
     * @return
     */
    @Override
    public List<Street> getStreetList(Map<String, Object> map) {
        return streetMapper.getStreetList(map);
    }
}
