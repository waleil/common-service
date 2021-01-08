package cn.net.yzl.pm.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户和角色关联表
 */
public class UserRole implements Serializable {
    private Long id;//主键id

    @NotNull(message = "userCode cant be null")
    private String userCode;//用户编号

    @NotNull(message = "roleId cant be null")
    private Integer roleId;//角色ID

    private Integer isDel;//是否删除（1:是 0:否）

    private Integer delSource;//删除来源 (1:BI 2:EHR)

    private Date createTime;//创建时间

    private String createCode;//创建人编号

    private Date updateTime;//修改时间

    private String updateCode;//修改人编号

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getDelSource() {
        return delSource;
    }

    public void setDelSource(Integer delSource) {
        this.delSource = delSource;
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
        sb.append(", userCode=").append(userCode);
        sb.append(", roleId=").append(roleId);
        sb.append(", isDel=").append(isDel);
        sb.append(", delSource=").append(delSource);
        sb.append(", createTime=").append(createTime);
        sb.append(", createCode=").append(createCode);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateCode=").append(updateCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}