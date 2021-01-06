package cn.net.yzl.common.zt.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.AssemblerResultUtil;
import cn.net.yzl.common.zt.entity.Regions;
import cn.net.yzl.common.zt.service.RegionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionsController {

    @Autowired
    private RegionsService regionsService;

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










}
