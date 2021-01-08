package cn.net.yzl.pm.model.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色信息表
 */
@Data
public class UserRoleVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userCode;//用户编号

    private Integer roleId;//角色id

    private String roleName;//角色名称

    private String roleDesc;//角色描述

    private Integer roleSort;//显示顺序

    private Integer dataScope;//数据范围（1:全部数据权限 2：自定数据权限 3:本部门数据权限 4:本部门及以下数据权限）

    private Integer menuCheckStrictly;//菜单树选择项是否关联显示

    private Integer deptCheckStrictly;//部门树选择项是否关联显示

}