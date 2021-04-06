package cn.net.yzl.msg.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * cn.net.yzl.staff.pojo.msg
 * 2021/2/5 9:32
 *
 * @author yangxiaopeng
 */
@Data
@ApiModel(value = "MsgTypePo", description = "消息类型")
public class MsgTypePo implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "消息类型名称", name = "name")
    private String name;


    @ApiModelProperty(value = "消息类型编号", name = "code")
    private String code;

    @ApiModelProperty(value = "系统类型(1：crm，2：ehr，3：dmc，4：bi)", name = "systemCode")
    private Integer systemCode;

    @ApiModelProperty(value = "是否删除（默认0：不删除，1：已删除）", name = "is_del")
    private String isDel;

    @ApiModelProperty(value = "创建人编号", name = "create_user")
    private String creator;


    @ApiModelProperty(value = "修改人编号", name = "update_user")
    private String updator;


    @ApiModelProperty(value="创建时间")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value="修改时间")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    
    private static final long serialVersionUID = 1L;
}
