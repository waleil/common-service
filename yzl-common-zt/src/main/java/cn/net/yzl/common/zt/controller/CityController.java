package cn.net.yzl.common.zt.controller;

import cn.net.yzl.common.dao.entity.City;
import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.service.CityService;
import cn.net.yzl.common.util.AssemblerResultUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     * 获取城市信息列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getCityList", method = RequestMethod.GET)
    public ComResponse getCityList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<City> cityList = cityService.getCityList();
        Page<City> commonPageVO = AssemblerResultUtil.resultAssembler(cityList);
        return ComResponse.success(commonPageVO);
    }










}
