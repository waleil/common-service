package cn.net.yzl.pm.service;

import cn.net.yzl.pm.entity.Role;

import java.util.List;

public interface RoleService {

    /**
     * 查询角色信息列表
     * @return
     */
    List<Role> getRoleList();

    /**
     * 新增角色信息
     * @param role
     * @return
     */
    int createRoleInfo(Role role);

    /**
     * 根据角色名称查询是否存在当前角色
     * @param roleName
     * @return
     */
    Role getRoleInfoByRoleName(String roleName);

    /**
     * 开启/关闭角色信息
     * @return
     */
    int updateRoleInfo(Integer roleId,Integer isEnable,String updateCode);

}
