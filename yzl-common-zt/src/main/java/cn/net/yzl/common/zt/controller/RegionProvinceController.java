package cn.net.yzl.common.zt.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.AssemblerResultUtil;
import cn.net.yzl.common.zt.entity.RegionProvince;
import cn.net.yzl.common.zt.service.RegionProvinceService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/regionProvince")
public class RegionProvinceController {

    @Autowired
    private RegionProvinceService regionProvinceService;

    /**
     * 查询大区省列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    //@OperateLog(operModule = "大区:查询大区列表", businessType = BusinessType.SELECT)
    @GetMapping("/getRegionProvinceList")
    public ComResponse getRegionProvinceList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                             @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<RegionProvince> regionProvinceList = regionProvinceService.getRegionProvinceList();
        Page<RegionProvince> commonPageVO = AssemblerResultUtil.resultAssembler(regionProvinceList);
        return ComResponse.success(commonPageVO);
    }








}
