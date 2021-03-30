package cn.net.yzl.msg.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * cn.net.yzl.msg.model.pojo
 * 2021/2/23 12:09
 *
 * @author yangxiaopeng
 */
@Slf4j
@Data
public class MsgItemPo implements Serializable {
//    private Integer id;

    @ApiModelProperty(value = "待办编码", name = "code")
    private String code;

//    @ApiModelProperty(value = "待办名称", name = "name")
//    private String name;

    @ApiModelProperty(value = "待办类型", name = "type")
    private Integer type;

//    @ApiModelProperty(value = "提醒时间", name = "remindTime")
//    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
//    private Date remindTime;
//
//    @ApiModelProperty(value = "详情", name = "detail")
//    private String detail;
//
//    @ApiModelProperty(value = "是否已提醒（默认0：未提醒，1：已提醒）", name = "isRemind")
//    private Integer isRemind;
//
//    @ApiModelProperty(value = "是否已读（默认0：未读，1：已读）", name = "isRead")
//    private Integer isRead;
//
//    @ApiModelProperty(value = "处理状态（默认0：未处理，1：已处理）", name = "dealStatus")
//    private String dealStatus;
//
//    @ApiModelProperty(value = "执行时间", name = "dealTime")
//    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
//    private Date dealTime;
//
//    @ApiModelProperty(value = "是否删除（默认0：不删除，1：删除）", name = "isDel")
//    private Integer isDel;

    @ApiModelProperty(value = "创建人编号", name = "creator")
    private String creator;

//    @ApiModelProperty(value = "修改人编号", name = "updator")
//    private String updator;
//
//    @ApiModelProperty(value = "创建时间", name = "createTime")
//    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
//    private Date createTime;
//
//    @ApiModelProperty(value = "修改时间", name = "updateTime")
//    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
//    private Date updateTime;

}
