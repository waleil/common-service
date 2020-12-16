package cn.net.yzl.common.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 国家表
 */
public class Country implements Serializable {
    private Integer id;//主键id

    private String zhName;//中文名称

    private String enName;//英文名称

    private String zhAbbr;//中文缩写

    private String enAbbr;//英文缩写

    private String zhfName;//中文全称

    private String enfName;//英文全称

    private String phonePrefix;//电话前缀

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;//创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;//修改时间

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName == null ? null : zhName.trim();
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public String getZhAbbr() {
        return zhAbbr;
    }

    public void setZhAbbr(String zhAbbr) {
        this.zhAbbr = zhAbbr == null ? null : zhAbbr.trim();
    }

    public String getEnAbbr() {
        return enAbbr;
    }

    public void setEnAbbr(String enAbbr) {
        this.enAbbr = enAbbr == null ? null : enAbbr.trim();
    }

    public String getZhfName() {
        return zhfName;
    }

    public void setZhfName(String zhfName) {
        this.zhfName = zhfName == null ? null : zhfName.trim();
    }

    public String getEnfName() {
        return enfName;
    }

    public void setEnfName(String enfName) {
        this.enfName = enfName == null ? null : enfName.trim();
    }

    public String getPhonePrefix() {
        return phonePrefix;
    }

    public void setPhonePrefix(String phonePrefix) {
        this.phonePrefix = phonePrefix == null ? null : phonePrefix.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", zhName=").append(zhName);
        sb.append(", enName=").append(enName);
        sb.append(", zhAbbr=").append(zhAbbr);
        sb.append(", enAbbr=").append(enAbbr);
        sb.append(", zhfName=").append(zhfName);
        sb.append(", enfName=").append(enfName);
        sb.append(", phonePrefix=").append(phonePrefix);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}