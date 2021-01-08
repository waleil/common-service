package cn.net.yzl.zt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 手机号码归属地址表
 */
public class MobileAttribution implements Serializable {
    private Integer id;//主键id

    private Integer startNumber;//手机地域开始号

    private Integer endNumber;//手机地域结束号

    private Short pvcId;//省份标识

    private Integer attributionId;//归属城市编号

    private String attributionName;//归属城市名称

    private Short code;//电话区号

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

    public Integer getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }

    public Integer getEndNumber() {
        return endNumber;
    }

    public void setEndNumber(Integer endNumber) {
        this.endNumber = endNumber;
    }

    public Short getPvcId() {
        return pvcId;
    }

    public void setPvcId(Short pvcId) {
        this.pvcId = pvcId;
    }

    public Integer getAttributionId() {
        return attributionId;
    }

    public void setAttributionId(Integer attributionId) {
        this.attributionId = attributionId;
    }

    public String getAttributionName() {
        return attributionName;
    }

    public void setAttributionName(String attributionName) {
        this.attributionName = attributionName == null ? null : attributionName.trim();
    }

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
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
        sb.append(", startNumber=").append(startNumber);
        sb.append(", endNumber=").append(endNumber);
        sb.append(", pvcId=").append(pvcId);
        sb.append(", attributionId=").append(attributionId);
        sb.append(", attributionName=").append(attributionName);
        sb.append(", code=").append(code);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}