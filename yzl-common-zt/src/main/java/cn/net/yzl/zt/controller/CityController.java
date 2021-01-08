package cn.net.yzl.zt.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.AssemblerResultUtil;
import cn.net.yzl.zt.entity.City;
import cn.net.yzl.zt.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     * 查询城市列表
     * @param provinceId
     * @return
     */
    //@OperateLog(operModule = "城市:查询城市信息列表", businessType = BusinessType.SELECT)
    @GetMapping("/getCityList")
    public ComResponse getCityList(@RequestParam Integer provinceId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("provinceId", provinceId);// 省份id
        List<City> cityList = cityService.getCityList(map);
        Page<City> commonPageVO = AssemblerResultUtil.resultAssemblerSingleList(cityList);
        return ComResponse.success(commonPageVO);
    }










}
