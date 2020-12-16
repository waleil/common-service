package cn.net.yzl.common.zt.controller;

import cn.net.yzl.common.dao.entity.Province;
import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.service.ProvinceService;
import cn.net.yzl.common.util.AssemblerResultUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    /**
     * 获取省份信息列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getProvinceList", method = RequestMethod.GET)
    public ComResponse getProvinceList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                       @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Province> provinceList = provinceService.getProvinceList();
        Page<Province> commonPageVO = AssemblerResultUtil.resultAssembler(provinceList);
        return ComResponse.success(commonPageVO);
    }


    @PostMapping("/createProvinceInfo")
    public ComResponse createProvinceInfo(@RequestBody Province province) {
        int i = provinceService.createProvinceInfo(province);
        if (i > 0) {
            return ComResponse.success(i).setMessage("创建省市信息成功");
        }else{
            return ComResponse.fail(ComResponse.ERROR_STATUS, "创建省市信息失败");
        }

    }










}
