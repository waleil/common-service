package cn.net.yzl.zt.service.impl;

import cn.net.yzl.zt.entity.Street;
import cn.net.yzl.zt.mapper.StreetMapper;
import cn.net.yzl.zt.service.StreetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("streetService")
public class StreetServiceImpl implements StreetService {

    @Resource
    private StreetMapper streetMapper;

    /**
     * 查询街道列表
     * @param areaId 地区id
     * @return
     */
    @Override
    public List<Street> getStreetList(Integer areaId) {
        log.info("查询街道列表areaId:{}", areaId);
        return streetMapper.getStreetList(areaId);
    }
}
