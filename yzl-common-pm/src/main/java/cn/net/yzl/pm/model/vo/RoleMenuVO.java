package cn.net.yzl.pm.model.vo;

import cn.net.yzl.pm.entity.RoleMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoleMenuVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer roleId;//角色id

    private String roleName;//角色名称

    private String roleDesc;//角色描述

    private List<RoleMenu> roleMenuList;//


}
