package cn.net.yzl.pm.service;

import cn.net.yzl.pm.model.dto.MenuDTO;
import cn.net.yzl.pm.model.dto.RoleDTO;
import cn.net.yzl.pm.model.vo.RoleMenuPermissionVO;
import cn.net.yzl.pm.model.vo.RoleMenuVO;

import java.util.List;

public interface RoleMenuService {

    /**
     * 根据角色id查询对应的菜单信息
     * @param roleId
     * @return
     */
    RoleMenuVO getRoleMenuListByRoleId(Integer roleId);

    List<RoleMenuPermissionVO> getRoleMenuListByRoleIds(List<Integer> roleIds);


    /**
     * 角色菜单信息
     * @param roleDTO
     * @return
     */
    int createOrUpdateRoleMenuInfoList(RoleDTO roleDTO);

    /**
     * 根据员工编号和菜单路由地址查询最高权限标识
     * @param userCode
     * @param menuUrl
     * @return
     */
    MenuDTO getIsAdminByUserCodeAndMenuUrl(String userCode, String menuUrl);

}
