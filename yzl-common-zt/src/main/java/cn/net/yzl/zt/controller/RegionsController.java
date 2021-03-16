package cn.net.yzl.zt.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.AssemblerResultUtil;
import cn.net.yzl.common.util.BeanUtil;
import cn.net.yzl.common.util.JsonUtil;
import cn.net.yzl.zt.entity.City;
import cn.net.yzl.zt.entity.Province;
import cn.net.yzl.zt.entity.Regions;
import cn.net.yzl.zt.model.dto.CityAll;
import cn.net.yzl.zt.model.dto.ProvinceAll;
import cn.net.yzl.zt.model.dto.RegionsAll;
import cn.net.yzl.zt.service.CityService;
import cn.net.yzl.zt.service.ProvinceService;
import cn.net.yzl.zt.service.RegionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionsController {

    @Autowired
    private RegionsService regionsService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CityService cityService;

    /**
     * 查询大区列表
     * @return
     */
    //@OperateLog(operModule = "大区:查询大区列表", businessType = BusinessType.SELECT)
    @GetMapping("/getRegionsList")
    public ComResponse getRegionsList(){
        List<Regions> regionsList = regionsService.getRegionsList();
        Page<Regions> commonPageVO = AssemblerResultUtil.resultAssemblerSingleList(regionsList);
        return ComResponse.success(commonPageVO);
    }

    @GetMapping("/getRegionsAllList")
    public ComResponse<List<RegionsAll>> getRegionsAllList(){
        List<Regions> regionsList = regionsService.getRegionsList();
        if(CollectionUtils.isEmpty(regionsList)){
            return ComResponse.success();
        }
        List<RegionsAll> regionsAllList = new ArrayList<>();
        for (Regions regions : regionsList) {
            RegionsAll regionsAll = new RegionsAll();
            BeanUtil.copyProperties(regions,regionsAll,true);
            List<Province> provinceList = provinceService.getProvinceList(null,regions.getRegionCode());
            if(CollectionUtils.isEmpty(provinceList)){
               continue;
            }
            List<ProvinceAll> provinceAllList = new ArrayList<>();
            for (Province province : provinceList) {
                ProvinceAll provinceAll = new ProvinceAll();
                BeanUtil.copyProperties(province,provinceAll,true);
                List<City> cityList = cityService.getCityList(province.getId());
                if(CollectionUtils.isEmpty(cityList)){
                    continue;
                }
                String cityListJson = JsonUtil.toJsonStr(cityList);
                List<CityAll> cityAllList = JsonUtil.jsonToList(cityListJson, CityAll.class);
                provinceAll.setCityAllList(cityAllList);
                provinceAllList.add(provinceAll);
            }
            regionsAll.setProvinceList(provinceAllList);
            regionsAllList.add(regionsAll);
        }
        return ComResponse.success(regionsAllList);
    }










}
