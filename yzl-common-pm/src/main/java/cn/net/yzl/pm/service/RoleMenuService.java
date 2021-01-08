package cn.net.yzl.pm.service;

import cn.net.yzl.pm.model.dto.RoleDTO;
import cn.net.yzl.pm.model.vo.RoleMenuVO;

import java.util.List;

public interface RoleMenuService {

    /**
     * 根据角色id查询对应的菜单信息
     * @param roleId
     * @return
     */
    RoleMenuVO getRoleMenuListByRoleId(Integer roleId);

    List<Integer> getRoleMenuListByRoleIds(List<Integer> roleIds);


    /**
     * 角色菜单信息
     * @param roleDTO
     * @return
     */
    int createOrUpdateRoleMenuInfoList(RoleDTO roleDTO);
}
