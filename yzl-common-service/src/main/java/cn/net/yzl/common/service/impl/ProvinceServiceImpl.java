package cn.net.yzl.common.service.impl;

import cn.net.yzl.common.dao.entity.Province;
import cn.net.yzl.common.dao.mapper.ProvinceMapper;
import cn.net.yzl.common.service.ProvinceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService {

    @Resource
    private ProvinceMapper provinceMapper;

    /**
     * 查询省份信息列表
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
