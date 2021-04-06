package cn.net.yzl.pm.mapper;

import cn.net.yzl.pm.entity.RoleMenu;
import cn.net.yzl.pm.model.dto.MenuDTO;
import cn.net.yzl.pm.model.vo.RoleMenuPermissionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    RoleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);

    /**
     * 根据角色id查询对应的菜单id信息
     * @param roleIds
     * @return
     */
    List<RoleMenuPermissionVO> getRoleMenuListByRoleIds(@Param("roleIds") List<Integer> roleIds);

    /**
     * 批量创建角色和菜单关联信息绑定
     * @param roleMenuList
     * @return
     */
    int batchCreateRoleMenuInfoList(@Param("list") List<RoleMenu> roleMenuList);

    /**
     * 批量修改角色和菜单关联信息绑定
     * @param roleMenuList
     * @return
     */
    int batchUpdateRoleMenuInfoList(@Param("list") List<RoleMenu> roleMenuList);

    /**
     * 根据角色id查询对应的菜单信息
     * @param roleId
     * @return
     */
    List<RoleMenu> getRoleMenuListByRoleId(Integer roleId);

    int deleteRoleMenuInfoByRoleId(Integer roleId);

    /**
     * 根据菜单id查询角色id
     * @param menuId
     * @return
     */
    List<Integer> getRoleIdsByMenuId(Integer menuId);

    /**
     * 根据员工编号和菜单路由地址查询最高权限标识
     * @param userCode
     * @param menuPath
     * @return
     */
    List<MenuDTO> getIsAdminByUserCodeAndMenuPath(@Param("userCode") String userCode, @Param("menuPath") String menuPath);
}