package cn.net.yzl.zt.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.AssemblerResultUtil;
import cn.net.yzl.zt.entity.Street;
import cn.net.yzl.zt.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/street")
public class StreetController {

    @Autowired
    private StreetService streetService;

    /**
     * 查询街道列表
     * @return
     */
    //@OperateLog(operModule = "街道:查询街道列表", businessType = BusinessType.SELECT)
    @GetMapping("/getStreetList")
    public ComResponse getStreetList(@RequestParam Integer areaId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("areaId", areaId);// 地区id
        List<Street> streetList = streetService.getStreetList(map);
        Page<Street> commonPageVO = AssemblerResultUtil.resultAssemblerSingleList(streetList);
        return ComResponse.success(commonPageVO);
    }










}
