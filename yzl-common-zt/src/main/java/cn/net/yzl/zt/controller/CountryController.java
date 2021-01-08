package cn.net.yzl.zt.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.AssemblerResultUtil;
import cn.net.yzl.zt.entity.Country;
import cn.net.yzl.zt.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    /**
     * 查询国家列表
     * @return
     */
    //@OperateLog(operModule = "国家:查询国家列表", businessType = BusinessType.SELECT)
    @GetMapping("/getCountryList")
    public ComResponse getCountryList(){
        List<Country> countryList = countryService.getCountryList();
        Page<Country> commonPageVO = AssemblerResultUtil.resultAssemblerSingleList(countryList);
        return ComResponse.success(commonPageVO);
    }










}
