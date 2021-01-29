package cn.net.yzl.pm.controller;

import cn.net.yzl.pm.service.RoleMenuService;
import cn.net.yzl.pm.service.UserRoleService;
import cn.net.yzl.pm.model.dto.UserRoleDTO;
import cn.net.yzl.pm.model.vo.UserRoleVO;
import cn.net.yzl.common.entity.ComResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PostMapping("/createUserRoleInfoList")
    public ComResponse createUserRoleInfoList(@RequestBody @Valid UserRoleDTO userRoleDTO) {
        int i = userRoleService.createUserRoleInfoList(userRoleDTO.getUserRoleList());
        if(i > 0){
            return ComResponse.success(i).setMessage("操作用户角色绑定关系信息成功");
        }
        return ComResponse.fail(ComResponse.ERROR_STATUS, "操作用户角色绑定关系信息失败");
    }

    /**
     * 根据用户编号查询绑定的角色信息
     * @param userCodes
     * @return
     */
    @GetMapping("/getUserRoleInfoByUserCodes")
    public ComResponse getRoleInfoByUserCodes(@RequestParam List<String> userCodes) {
        List<UserRoleVO> roleList = userRoleService.getUserRoleInfoByUserCodes(userCodes);
        return ComResponse.success(roleList);
    }


}
