package cn.net.yzl.zt.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.AssemblerResultUtil;
import cn.net.yzl.zt.entity.City;
import cn.net.yzl.zt.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
        if(provinceId == null){//省份id
            return ComResponse.fail(ComResponse.ERROR_STATUS, "参数错误");
        }
        List<City> cityList = cityService.getCityList(provinceId);
        Page<City> commonPageVO = AssemblerResultUtil.resultAssemblerSingleList(cityList);
        return ComResponse.success(commonPageVO);
    }










}
