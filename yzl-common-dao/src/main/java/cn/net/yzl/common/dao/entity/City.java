package cn.net.yzl.common.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 城市表
 */
public class City implements Serializable {

    private Integer id;//主键id

    private String name;//城市名称

    private Integer pvcId;//省的主键

    private Short pvcCapital;//是否为省会城市

    private Short phonePrefixCode;//座机电话区号

    private Short phoneLength;//座机电话长度

    private String enAbbr;//城市拼音简写

    private String zip;//邮编

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPvcId() {
        return pvcId;
    }

    public void setPvcId(Integer pvcId) {
        this.pvcId = pvcId;
    }

    public Short getPvcCapital() {
        return pvcCapital;
    }

    public void setPvcCapital(Short pvcCapital) {
        this.pvcCapital = pvcCapital;
    }

    public Short getPhonePrefixCode() {
        return phonePrefixCode;
    }

    public void setPhonePrefixCode(Short phonePrefixCode) {
        this.phonePrefixCode = phonePrefixCode;
    }

    public Short getPhoneLength() {
        return phoneLength;
    }

    public void setPhoneLength(Short phoneLength) {
        this.phoneLength = phoneLength;
    }

    public String getEnAbbr() {
        return enAbbr;
    }

    public void setEnAbbr(String enAbbr) {
        this.enAbbr = enAbbr == null ? null : enAbbr.trim();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip == null ? null : zip.trim();
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
        sb.append(", name=").append(name);
        sb.append(", pvcId=").append(pvcId);
        sb.append(", pvcCapital=").append(pvcCapital);
        sb.append(", phonePrefixCode=").append(phonePrefixCode);
        sb.append(", phoneLength=").append(phoneLength);
        sb.append(", enAbbr=").append(enAbbr);
        sb.append(", zip=").append(zip);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}