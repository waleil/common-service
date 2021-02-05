package cn.net.yzl.pm.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer menuId;//菜单id

    private String menuName;//菜单名称

    private Integer parentId;//父菜单ID

    private Integer orderNum;//显示顺序

    private Integer permissionType;//权限类型(1:数据权限 2:报表权限3:功能权限)

    private String menuPath;//菜单配置地址URL

    private Integer openWay;//打开方式(1:新窗口 2:内嵌)

    private String component;//组件路径

    private Integer isFrame;//是否为外链（1:是 0:否）

    private String menuType;//菜单类型（M:目录 C:菜单 F:按钮）

    private String perms;//权限标识

    private String icon;//菜单图标

    private Integer isEdit;//是否可编辑（1:是 0:否）

    private Integer isLook;//是否可查看（1:是 0:否）


}
