package cn.net.yzl.common.zt.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.AssemblerResultUtil;
import cn.net.yzl.common.zt.entity.City;
import cn.net.yzl.common.zt.service.CityService;
import com.github.pagehelper.PageHelper;
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
     * @param pageNum
     * @param pageSize
     * @return
     */
    //@OperateLog(operModule = "城市:查询城市信息列表", businessType = BusinessType.SELECT)
    @GetMapping("/getCityList")
    public ComResponse getCityList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                   @RequestParam(required = false) Integer provinceId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("provinceId", provinceId);// 省份id
        PageHelper.startPage(pageNum, pageSize);
        List<City> cityList = cityService.getCityList(map);
        Page<City> commonPageVO = AssemblerResultUtil.resultAssembler(cityList);
        return ComResponse.success(commonPageVO);
    }










}
