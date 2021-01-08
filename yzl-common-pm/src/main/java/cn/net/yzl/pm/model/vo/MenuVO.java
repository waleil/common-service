package cn.net.yzl.pm.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String menuName;//菜单名称

    private Integer parentId;//父菜单ID

    private Integer orderNum;//显示顺序

    private Integer permissionType;//权限类型(1:数据权限 2:报表权限3:功能权限)

    private String settingPath;//配置地址URL

    private String component;//组件路径

    private Integer isFrame;//是否为外链（1:是 0:否）

    private String menuType;//菜单类型（M:目录 C:菜单 F:按钮）

    private String perms;//权限标识

    private String icon;//菜单图标


}
