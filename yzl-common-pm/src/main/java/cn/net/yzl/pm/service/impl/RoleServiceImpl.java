package cn.net.yzl.pm.service.impl;

import cn.net.yzl.pm.service.RoleService;
import cn.net.yzl.pm.entity.Role;
import cn.net.yzl.pm.mapper.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 查询角色信息列表
     * @return
     */
    @Override
    public List<Role> getRoleList() {
            return roleMapper.getRoleList();
    }

    /**
     * 新增角色信息
     * @param role
     * @return
     */
    @Override
    public int createRoleInfo(Role role) {
        //查询最大序号
        int sort = roleMapper.getRoleMaxSort();
        role.setRoleSort(++sort);
        return roleMapper.insertSelective(role);

    }

    /**
     * 根据角色名称查询是否存在当前角色
     * @param roleName
     * @return
     */
    @Override
    public Role getRoleInfoByRoleName(String roleName) {

        return roleMapper.getRoleInfoByRoleName(roleName);
    }

}
