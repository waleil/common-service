package cn.net.yzl.pm.model.vo;

import lombok.Data;
import java.io.Serializable;

@Data
public class RoleMenuPermissionVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer menuId;//菜单id

    private Integer isEdit;//是否可编辑（1:是 0:否）

    private Integer isLook;//是否可查看（1:是 0:否）


}
