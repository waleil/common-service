package cn.net.yzl.zt.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.AssemblerResultUtil;
import cn.net.yzl.zt.entity.MobileAttribution;
import cn.net.yzl.zt.service.MobileAttributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mobileAttribution")
public class MobileAttributionController {

    @Autowired
    private MobileAttributionService mobileAttributionListService;

    /**
     * 查询手机号归属地列表
     * @return
     */
    //@OperateLog(operModule = "手机号归属地:查询手机号归属地列表", businessType = BusinessType.SELECT)
    @GetMapping("/getMobileAttributionList")
    public ComResponse getMobileAttributionList(){
        List<MobileAttribution> mobileAttributionList = mobileAttributionListService.getMobileAttributionList();
        Page<MobileAttribution> commonPageVO = AssemblerResultUtil.resultAssemblerSingleList(mobileAttributionList);
        return ComResponse.success(commonPageVO);
    }










}
