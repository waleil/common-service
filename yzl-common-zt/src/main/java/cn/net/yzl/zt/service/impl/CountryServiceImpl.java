package cn.net.yzl.zt.service.impl;

import cn.net.yzl.zt.entity.Country;
import cn.net.yzl.zt.mapper.CountryMapper;
import cn.net.yzl.zt.service.CountryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("countryService")
public class CountryServiceImpl implements CountryService {

    @Resource
    private CountryMapper countryMapper;

    /**
     * 查询国家列表
     * @return
     */
    @Override
    public List<Country> getCountryList() {
        return countryMapper.getCountryList();
    }
}
