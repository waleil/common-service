package cn.net.yzl.zt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 地区表(区或县)
 */
public class Area implements Serializable {
    private Integer id;//主键id

    private Integer cityId;//城市主键

    private String name;//名称

    private String enAbbr;//区域拼音简写

    private String hubCode;//物流编码

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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public Date getcreateTime() {
        return createTime;
    }

    public void setcreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getupdateTime() {
        return updateTime;
    }

    public void setupdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getHubCode() {
        return hubCode;
    }

    public void setHubCode(String hubCode) {
        this.hubCode = hubCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cityId=").append(cityId);
        sb.append(", name=").append(name);
        sb.append(", enAbbr=").append(enAbbr);
        sb.append(", hubCode=").append(hubCode);
        sb.append(", zip=").append(zip);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}