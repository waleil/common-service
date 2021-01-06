package cn.net.yzl.common.zt.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.AssemblerResultUtil;
import cn.net.yzl.common.zt.entity.Country;
import cn.net.yzl.common.zt.service.CountryService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    /**
     * 查询国家列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    //@OperateLog(operModule = "国家:查询国家列表", businessType = BusinessType.SELECT)
    @GetMapping("/getCountryList")
    public ComResponse getCountryList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                      @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Country> countryList = countryService.getCountryList();
        Page<Country> commonPageVO = AssemblerResultUtil.resultAssembler(countryList);
        return ComResponse.success(commonPageVO);
    }










}
