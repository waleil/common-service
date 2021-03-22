package cn.net.yzl.zt.service.impl;

import cn.net.yzl.zt.entity.Nation;
import cn.net.yzl.zt.mapper.NationMapper;
import cn.net.yzl.zt.service.NationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("nationService")
public class NationServiceImpl implements NationService {

    @Resource
    private NationMapper nationMapper;

    /**
     * 获取民族列表
     * @param map
     * @return
     */
    @Override
    public List<Nation> getNationList(Map<String, Object> map) {
        log.info("获取民族列表nationMap:{}", map);
        return nationMapper.getNationList(map);
    }
}
