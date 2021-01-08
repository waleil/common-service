package cn.net.yzl.pm.model.dto;

import cn.net.yzl.pm.entity.Role;
import cn.net.yzl.pm.entity.RoleMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 添加角色配置菜单
 */
@Data
public class RoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Role role;//角色信息
    private List<RoleMenu> roleMenuList;//角色关联菜单信息集合


}
