package cn.net.yzl.msg.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/2/7 21:35
 * @title: PersonMsgInfoSelectDto
 * @description:
 */
@Data
@ApiModel(value = "PersonMsgInfoSelectDto",description = "个人中心-消息列表")
public class PersonMsgInfoSelectDto {

    @ApiModelProperty(value = "消息编码",name = "msgCode")
    private String msgCode;

    @ApiModelProperty(value = "消息标题",name = "title")
    private String title;

    @ApiModelProperty(value = "消息类别",name = "typeName")
    private String typeName;

    @ApiModelProperty(value = "消息内容",name = "content")
    private String content;

    @ApiModelProperty(value = "消息附件",name = "fileUrl")
    private String fileUrl;

    @ApiModelProperty(value = "发送人编号",name = "sendUser")
    private String sendUser;

    @ApiModelProperty(value = "发送人名称",name = "sendName")
    private String sendName;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发送时间",name = "sendTime")
    private Date sendTime;

    @ApiModelProperty(value = "消息状态",name = "isRead")
    private Integer isRead;

}
