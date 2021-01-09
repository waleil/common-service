package cn.net.yzl.zt.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.AssemblerResultUtil;
import cn.net.yzl.zt.entity.Province;
import cn.net.yzl.zt.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    /**
     * 查询省份列表
     * @param regionCode
     * @return
     */
    @RequestMapping(value = "/getProvinceList", method = RequestMethod.GET)
    public ComResponse getProvinceList( @RequestParam(required = false) Integer countryId,
                                        @RequestParam(required = false) String regionCode){

//        Map<String, Object> map = new HashMap<String, Object>();
//        if(StringUtils.hasText(regionCode)) {
//            map.put("regionCode", regionCode);// 大区编号
//        }
//        if(countryId != null) {
//            map.put("provinceId", countryId);// 省份id
//        }
     //   return ComResponse.fail(ComResponse.ERROR_STATUS, "参数错误");

        List<Province> provinceList = provinceService.getProvinceList(countryId,regionCode);
        Page<Province> commonPageVO = AssemblerResultUtil.resultAssemblerSingleList(provinceList);
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
