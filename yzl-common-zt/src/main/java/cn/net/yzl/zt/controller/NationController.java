package cn.net.yzl.zt.controller;

import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.AssemblerResultUtil;
import cn.net.yzl.zt.entity.Nation;
import cn.net.yzl.zt.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/nation")
public class NationController {

    @Autowired
    private NationService nationService;

    /**
     * 查询民族列表
     * @param code
     * @param name
     * @param enName
     * @return
     */
    //@OperateLog(operModule = "民族:查询民族信息列表", businessType = BusinessType.SELECT)
    @GetMapping("/getNationList")
    public ComResponse getNationList(@RequestParam(required = false) Integer code,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(required = false) String enName){
        Map<String,Object> map = new HashMap<String,Object>();
        if(code != null){//民族编号
            map.put("code",code);
        }
        if(StringUtils.hasText(name)){//民族名称
            map.put("name",name);
        }
        if(StringUtils.hasText(enName)){//拼音简拼
            map.put("enName",enName);
        }
        List<Nation> nationList = nationService.getNationList(map);
        Page<Nation> commonPageVO = AssemblerResultUtil.resultAssemblerSingleList(nationList);
        return ComResponse.success(commonPageVO);
    }










}
