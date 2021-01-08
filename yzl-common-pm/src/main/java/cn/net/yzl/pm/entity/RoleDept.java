package cn.net.yzl.pm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色和部门关联表
 */
public class RoleDept implements Serializable {
    private Integer id;//主键id

    private Integer roleId;//角色ID

    private Integer deptId;//部门ID

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

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
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
        sb.append(", roleId=").append(roleId);
        sb.append(", deptId=").append(deptId);
        sb.append(", createTime=").append(createTime);
        sb.append(", createCode=").append(createCode);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateCode=").append(updateCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}