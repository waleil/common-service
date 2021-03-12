package cn.net.yzl.pm.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色和菜单关联表
 */
public class RoleMenu implements Serializable {
    private Integer id;//主键id

    private Integer roleId;//角色ID

    @NotNull(message = "menuId cant be null")
    private Integer menuId;//菜单ID

    private String menuName;//菜单名称

    private String menuPath;//菜单地址

    private Integer menuParentId;//父菜单ID

    private Integer orderNum;//显示顺序

    private Integer isEdit;//是否可编辑（1:是 0:否）

    private Integer isLook;//是否可查看（1:是 0:否）

    private Integer isAdmin;//是否最高权限（1:是 0:否）

    private Date createTime;//创建时间

    private String createCode;//创建人编号

    private Date updateTime;//修改时间

    private String updateCode;//修改人编号

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Integer isEdit) {
        this.isEdit = isEdit;
    }

    public Integer getIsLook() {
        return isLook;
    }

    public void setIsLook(Integer isLook) {
        this.isLook = isLook;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateCode() {
        return createCode;
    }

    public void setCreateCode(String createCode) {
        this.createCode = createCode == null ? null : createCode.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateCode() {
        return updateCode;
    }

    public void setUpdateCode(String updateCode) {
        this.updateCode = updateCode == null ? null : updateCode.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(Integer menuParentId) {
        this.menuParentId = menuParentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", menuId=").append(menuId);
        sb.append(", menuName=").append(menuName);
        sb.append(", menuPath=").append(menuPath);
        sb.append(", menuParentId=").append(menuParentId);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", isEdit=").append(isEdit);
        sb.append(", isLook=").append(isLook);
        sb.append(", isAdmin=").append(isAdmin);
        sb.append(", createTime=").append(createTime);
        sb.append(", createCode=").append(createCode);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateCode=").append(updateCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}