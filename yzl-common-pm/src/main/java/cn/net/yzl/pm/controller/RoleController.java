package cn.net.yzl.pm.controller;

import cn.net.yzl.pm.entity.Role;
import cn.net.yzl.pm.model.constant.CommonConstant;
import cn.net.yzl.pm.model.enums.RoleStatusEnums;
import cn.net.yzl.pm.service.RoleService;
import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.AssemblerResultUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {


    @Autowired
    private RoleService roleService;

    /**
     * 查询角色信息列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getRoleList")
    public ComResponse getRoleList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roleList = roleService.getRoleList();
        Page<Role> commonPageVO = AssemblerResultUtil.resultAssembler(roleList);
        return ComResponse.success(commonPageVO);
    }


    /**
     * 新增角色信息
     * Param biRole
     * @return
     */
    @PostMapping("/createRoleInfo")
    public ComResponse createRoleInfo(@RequestBody Role role) {
        int i = roleService.createRoleInfo(role);
        if(i > 0){
            return ComResponse.success(i).setMessage("创建角色信息成功");
        }
        return ComResponse.fail(ComResponse.ERROR_STATUS, "创建角色信息失败");
    }

    /**
     * 开启/关闭角色信息
     * @param roleId 角色id
     * @param isEnable 是否开启（1:启用 0:禁用)
     * @return
     */
    @GetMapping("/updateRoleInfo")
    public ComResponse updateRoleInfo(@RequestParam Integer roleId,
                                      @RequestParam Integer isEnable,
                                      @RequestParam(required = false) String updateCode) {
        String operationName = "";
        if(isEnable == RoleStatusEnums.ENABLE_STATUS.getCode()){
            operationName = RoleStatusEnums.ENABLE_STATUS.getName();
        }else{
            operationName = RoleStatusEnums.CLOSE_STATUS.getName();
        }
        int i = roleService.updateRoleInfo(roleId,isEnable,updateCode);
        if(i > 0){
            return ComResponse.success(i).setMessage(operationName+"角色信息成功");
        }
        return ComResponse.fail(ComResponse.ERROR_STATUS, operationName+"角色信息失败");
    }
}
