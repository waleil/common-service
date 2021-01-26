package cn.net.yzl.zt.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.zt.entity.RegionProvince;
import cn.net.yzl.zt.service.RegionProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/regionProvince")
public class RegionProvinceController {

    @Autowired
    private RegionProvinceService regionProvinceService;

    /**
     * 查询大区省列表
     * @return
     */
    //@OperateLog(operModule = "大区:查询大区列表", businessType = BusinessType.SELECT)
    @GetMapping("/getRegionProvinceList")
    public ComResponse getRegionProvinceList(){

        List<RegionProvince> regionProvinceList = regionProvinceService.getRegionProvinceList();
        Map<String, List<RegionProvince>> regionProvinceListMap = regionProvinceList.stream().collect(Collectors.groupingBy(RegionProvince::getRegionName));
        return ComResponse.success(regionProvinceListMap);
    }








}
