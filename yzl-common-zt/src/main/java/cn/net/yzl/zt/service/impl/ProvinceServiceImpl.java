package cn.net.yzl.zt.service.impl;

import cn.net.yzl.zt.entity.Province;
import cn.net.yzl.zt.mapper.ProvinceMapper;
import cn.net.yzl.zt.service.ProvinceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService {

    @Resource
    private ProvinceMapper provinceMapper;

    /**
     * 查询省份列表
     * @return
     */
    @Override
    public List<Province> getProvinceList(Map<String,Object> map) {
        return provinceMapper.getProvinceList(map);
    }

    @Override
    public int createProvinceInfo(Province province) {
        return provinceMapper.insertSelective(province);
    }
}
