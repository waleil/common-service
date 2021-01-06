package cn.net.yzl.common.zt.service.impl;

import cn.net.yzl.common.zt.entity.Province;
import cn.net.yzl.common.zt.mapper.ProvinceMapper;
import cn.net.yzl.common.zt.service.ProvinceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService {

    @Resource
    private ProvinceMapper provinceMapper;

    /**
     * 查询省份列表
     * @return
     */
    @Override
    public List<Province> getProvinceList() {
        return provinceMapper.getProvinceList();
    }

    @Override
    public int createProvinceInfo(Province province) {
        return provinceMapper.insertSelective(province);
    }
}
