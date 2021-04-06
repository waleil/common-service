package cn.net.yzl.msg.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * cn.net.yzl.msg.model.pojo
 * 2021/2/25 13:44
 *
 * @author yangxiaopeng
 */
@Data
public class MsgTemplateDto {
    //private Integer id;

    @ApiModelProperty(value = "模版编号", name = "code")
    private String code;

    @ApiModelProperty(value = "系统类型(1：crm，2：ehr，3：dmc，4：bi)", name = "systemCode")
    private Integer systemCode;

    @ApiModelProperty(value = "模版类型", name = "type")
    private Integer type;

    @ApiModelProperty(value = "模板内容", name = "content")
    private String content;

    @ApiModelProperty(value = "是否删除（默认0：不删除，1：已删除", name = "isDel")
    private Integer isDel;

    @ApiModelProperty(value = "接收人编号", name = "userCode")
    private String userCode;

    @ApiModelProperty(value = "创建人编号", name = "creator")
    private String creator;

    @ApiModelProperty(value = "修改人编号", name = "updator")
    private String updator;

    @ApiModelProperty(value = "创建时间", name = "createTime")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "修改时间", name = "updateTime")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty(value = "动态参数", name = "params")
    private Object[] params;


}
