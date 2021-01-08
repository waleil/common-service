package cn.net.yzl.pm.controller;

import cn.net.yzl.pm.entity.Role;
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

}
