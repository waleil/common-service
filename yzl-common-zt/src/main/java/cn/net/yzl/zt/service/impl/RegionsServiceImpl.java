package cn.net.yzl.zt.service.impl;

import cn.net.yzl.common.util.JsonUtil;
import cn.net.yzl.zt.config.redis.RedisUtil;
import cn.net.yzl.zt.entity.RegionProvince;
import cn.net.yzl.zt.entity.Regions;
import cn.net.yzl.zt.mapper.RegionsMapper;
import cn.net.yzl.zt.model.constant.RedisConstant;
import cn.net.yzl.zt.service.RegionsService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("regionsService")
public class RegionsServiceImpl implements RegionsService {

    @Resource
    private RegionsMapper regionsMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询大区列表
     * @return
     */
    @Override
    public List<Regions> getRegionsList() {
        List<Regions> regionsList;
        String regions = (String) redisUtil.get(RedisConstant.REGION_LIST);
        if(StringUtils.hasText(regions)) {
            regionsList = JSONObject.parseArray(regions, Regions.class);
            log.info("查询缓存regions:{}", JsonUtil.toJsonStr(regionsList));
        }else {
            regionsList = regionsMapper.getRegionsList();
            log.info("查询数据库regions:{}", JsonUtil.toJsonStr(regionsList));
            if(!CollectionUtils.isEmpty(regionsList)){
                regions = JSONObject.toJSONString(regionsList);
                redisUtil.set(RedisConstant.REGION_LIST, regions, RedisConstant.SECOND_SEVEN_EXPIRE_TIME);//7天缓存
            }
        }
        return regionsList;
    }
}
