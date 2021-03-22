package cn.net.yzl.zt.service.impl;

import cn.net.yzl.common.util.JsonUtil;
import cn.net.yzl.common.util.YLoggerUtil;
import cn.net.yzl.zt.config.redis.RedisUtil;
import cn.net.yzl.zt.entity.RegionProvince;
import cn.net.yzl.zt.mapper.RegionProvinceMapper;
import cn.net.yzl.zt.model.constant.RedisConstant;
import cn.net.yzl.zt.service.RegionProvinceService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("regionProvinceService")
public class RegionProvinceServiceImpl implements RegionProvinceService {

    @Resource
    private RegionProvinceMapper regionProvinceMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询大区省列表
     * @return
     */
    @Override
    public List<RegionProvince> getRegionProvinceList() {
        List<RegionProvince> regionProvinceList;
        String regionProvince = (String) redisUtil.get(RedisConstant.REGIONPROVINCE_LIST);
        if(StringUtils.hasText(regionProvince)) {
            regionProvinceList = JSONObject.parseArray(regionProvince, RegionProvince.class);
            log.info("查询缓存regionProvince:{}", JsonUtil.toJsonStr(regionProvinceList));
        }else {
            regionProvinceList = regionProvinceMapper.getRegionProvinceList();
            log.info("查询数据库regionProvince:{}", JsonUtil.toJsonStr(regionProvinceList));
            if(!CollectionUtils.isEmpty(regionProvinceList)){
                regionProvince = JSONObject.toJSONString(regionProvinceList);
                redisUtil.set(RedisConstant.REGIONPROVINCE_LIST, regionProvince, RedisConstant.SECOND_SEVEN_EXPIRE_TIME);//7天缓存
            }
        }
        return regionProvinceList;
    }
}
