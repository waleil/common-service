package cn.net.yzl.pm.mapper;

import cn.net.yzl.pm.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 查询角色信息列表
     * @return
     */
    List<Role> getRoleList();

    /**
     * 查询角色当前最大sort显示顺序
     * @return
     */
    int getRoleMaxSort();

    /**
     * 根据角色名称查询是否存在当前角色
     * @param roleName
     * @return
     */
    Role getRoleInfoByRoleName(String roleName);

    /**
     * 开启/关闭角色信息
     * @param roleId
     * @param isEnable
     * @param updateCode
     * @return
     */
    int updateRoleInfo(@Param("roleId") Integer roleId,@Param("isEnable") Integer isEnable,@Param("updateCode") String updateCode);
}