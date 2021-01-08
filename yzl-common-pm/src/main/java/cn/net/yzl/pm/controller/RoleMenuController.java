package cn.net.yzl.pm.controller;

import cn.net.yzl.pm.entity.Role;
import cn.net.yzl.pm.service.RoleMenuService;
import cn.net.yzl.pm.service.RoleService;
import cn.net.yzl.pm.model.dto.RoleDTO;
import cn.net.yzl.pm.model.vo.RoleMenuVO;
import cn.net.yzl.common.entity.ComResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 创建/修改角色和菜单关联信息绑定
     * Param biRole
     * @return
     */
    @PostMapping("/createOrUpdateRoleMenuInfoList")
    public ComResponse createOrUpdateRoleMenuInfoList(@RequestBody RoleDTO roleDTO) {
        String operationName = "";
        if(roleDTO != null) {
            //根据角色名称查询是否存在当前角色
            Role role = roleService.getRoleInfoByRoleName(roleDTO.getRole().getRoleName());
            if(roleDTO.getRole().getId() != null){
                if(role != null) {
                    if (role.getId() != roleDTO.getRole().getId()) {
                        return ComResponse.fail(ComResponse.ERROR_STATUS, "当前角色信息已存在");
                    }
                }
                operationName = "修改";
            }else{
                if(role != null){
                    return ComResponse.fail(ComResponse.ERROR_STATUS, "当前角色信息已存在");
                }
                operationName = "创建";

            }
        }else{
            return ComResponse.fail(ComResponse.ERROR_STATUS, "参数错误");
        }
        int i = roleMenuService.createOrUpdateRoleMenuInfoList(roleDTO);
        if(i > 0){
            return ComResponse.success(i).setMessage(operationName+"角色菜单关联信息成功");
        }
        return ComResponse.fail(ComResponse.ERROR_STATUS, operationName+"角色菜单关联信息失败");
    }

    /**
     * 根据角色id查询对应的菜单信息
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/getRoleMenuListByRoleId", method = RequestMethod.GET)
    public ComResponse getRoleMenuListByRoleId(@RequestParam Integer roleId) {
        RoleMenuVO roleMenuVO = roleMenuService.getRoleMenuListByRoleId(roleId);
        return ComResponse.success(roleMenuVO);

    }

}
