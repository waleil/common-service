package cn.net.yzl.common.zt.model.vo.menu;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class MenuAdd {
    private Integer id;//主键id

    @NotNull(message = "menuName cant be null")
    private String menuName;//菜单名称


    private Integer parentId;//父菜单ID

    private Integer orderNum;//显示顺序

    private Integer permissionType;//权限类型(1:数据权限 2:报表权限3:功能权限)

    @NotNull(message = "settingPath cant be null")
    @Email(message = "settingPath不是一个email地址")
    private String settingPath;//配置地址URL

    private String component;//组件路径

    private Integer isFrame;//是否为外链（1:是 0:否）

    private String menuType;//菜单类型（M:目录 C:菜单 F:按钮）

    private Integer isEnable;//是否开启（ 1:启用 0:禁用）

    private Integer isDel;//是否删除（1:是 0:否）

    private String perms;//权限标识

    private String icon;//菜单图标

    private String remark;//备注

    private Date createTime;//创建时间

    private String createCode;//创建人编号

    private Date updateTime;//修改时间

    private String updateCode;//修改人编号
}
