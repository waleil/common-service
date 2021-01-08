package cn.net.yzl.zt.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.AssemblerResultUtil;
import cn.net.yzl.zt.entity.Area;
import cn.net.yzl.zt.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    /**
     * 查询地区列表
     * @param cityId
     * @return
     */
    //@OperateLog(operModule = "地区:查询地区列表", businessType = BusinessType.SELECT)
    @GetMapping("/getAreaList")
    public ComResponse getAreaList(@RequestParam Integer cityId){

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cityId", cityId);// 城市id
        List<Area> areaList = areaService.getAreaList(map);
        Page<Area> commonPageVO = AssemblerResultUtil.resultAssemblerSingleList(areaList);
        return ComResponse.success(commonPageVO);
    }



}
