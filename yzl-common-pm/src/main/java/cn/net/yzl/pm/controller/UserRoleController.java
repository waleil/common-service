package cn.net.yzl.pm.controller;

import cn.net.yzl.pm.service.RoleMenuService;
import cn.net.yzl.pm.service.UserRoleService;
import cn.net.yzl.pm.model.dto.UserRoleDTO;
import cn.net.yzl.pm.model.vo.UserRoleVO;
import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.operatelogger.annotate.OperateLog;
import cn.net.yzl.operatelogger.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {


    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 单个/批量创建用户和角色关联信息绑定
     * Param biRole
     * @return
     */
    @OperateLog(operModule = "用户角色模块:单个/批量创建方法", businessType = BusinessType.INSERT)
    @PostMapping("/createUserRoleInfoList")
    public ComResponse createUserRoleInfoList(@RequestBody UserRoleDTO userRoleDTO) {
        int i = userRoleService.createUserRoleInfoList(userRoleDTO.getUserRoleList());
        if(i > 0){
            return ComResponse.success(i).setMessage("创建用户角色绑定关系信息成功");
        }
        return ComResponse.fail(ComResponse.ERROR_STATUS, "创建用户角色绑定关系信息失败");
    }

    /**
     * 根据用户编号查询绑定的角色信息
     * @param userCodes
     * @return
     */
    @OperateLog(operModule = "角色模块:查询用户绑定的角色方法", businessType = BusinessType.SELECT)
    @GetMapping("/getUserRoleInfoByUserCodes")
    public ComResponse getRoleInfoByUserCodes(@RequestParam List<String> userCodes) {
        List<UserRoleVO> roleList = userRoleService.getUserRoleInfoByUserCodes(userCodes);
        return ComResponse.success(roleList);
    }


}
