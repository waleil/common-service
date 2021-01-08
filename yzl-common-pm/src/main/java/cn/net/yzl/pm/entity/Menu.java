package cn.net.yzl.pm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单表
 */
public class Menu implements Serializable {
    private Integer id;//主键id

    private String menuName;//菜单名称

    private Integer parentId;//父菜单ID

    private Integer orderNum;//显示顺序

    private Integer permissionType;//权限类型(1:数据权限 2:报表权限3:功能权限)

    private String menuPath;//配置地址URL

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

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component == null ? null : component.trim();
    }

    public Integer getIsFrame() {
        return isFrame;
    }

    public void setIsFrame(Integer isFrame) {
        this.isFrame = isFrame;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms == null ? null : perms.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", menuName=").append(menuName);
        sb.append(", parentId=").append(parentId);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", permissionType=").append(permissionType);
        sb.append(", menuPath=").append(menuPath);
        sb.append(", component=").append(component);
        sb.append(", isFrame=").append(isFrame);
        sb.append(", menuType=").append(menuType);
        sb.append(", isEnable=").append(isEnable);
        sb.append(", isDel=").append(isDel);
        sb.append(", perms=").append(perms);
        sb.append(", icon=").append(icon);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", createCode=").append(createCode);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateCode=").append(updateCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}