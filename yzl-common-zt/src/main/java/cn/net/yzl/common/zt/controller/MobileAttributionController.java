package cn.net.yzl.common.zt.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.AssemblerResultUtil;
import cn.net.yzl.common.zt.entity.MobileAttribution;
import cn.net.yzl.common.zt.service.MobileAttributionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mobileAttribution")
public class MobileAttributionController {

    @Autowired
    private MobileAttributionService mobileAttributionListService;

    /**
     * 查询手机号归属地列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    //@OperateLog(operModule = "手机号归属地:查询手机号归属地列表", businessType = BusinessType.SELECT)
    @GetMapping("/getMobileAttributionList")
    public ComResponse getMobileAttributionList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<MobileAttribution> mobileAttributionList = mobileAttributionListService.getMobileAttributionList();
        Page<MobileAttribution> commonPageVO = AssemblerResultUtil.resultAssembler(mobileAttributionList);
        return ComResponse.success(commonPageVO);
    }










}
