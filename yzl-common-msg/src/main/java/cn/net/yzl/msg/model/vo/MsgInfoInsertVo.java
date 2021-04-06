package cn.net.yzl.msg.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/2/7 9:50
 * @title: MsgInfoVo
 * @description:
 */
@Data
@ApiModel(value = "MsgInfoVo",description = "消息新增")
public class MsgInfoInsertVo {

    @ApiModelProperty(value = "消息编号（不用传，后台生成）",name = "code")
    private String code;

    @ApiModelProperty(value = "消息标题",name = "title")
    private String title;

    @ApiModelProperty(value = "消息类别",name = "typeCode")
    private String typeCode;

    @ApiModelProperty(value = "消息内容",name = "content")
    private String content;

    @ApiModelProperty(value = "消息附件",name = "fileUrl")
    private String fileUrl;

    @ApiModelProperty(value = "发送对象（0：全部员工，1：部分员工）",name = "target")
    private Integer target;

    @ApiModelProperty(value = "部分员工编号",name = "targetList")
    private String targetList;

    @ApiModelProperty(value = "发送状态（0：待发送，1：已发送，2：取消发送）",name = "sendStatus")
    private Integer sendStatus;

    @ApiModelProperty(value = "通知方式（默认0：系统消息）",name = "noticeMode")
    private Integer noticeMode;

    @ApiModelProperty(value = "发送方式（1：实时发送，2：定时发送）",name = "sendMode")
    private Integer sendMode;

    @ApiModelProperty(value = "发送人编号",name = "sendUser")
    private String sendUser;

    @ApiModelProperty(value = "发送人名称",name = "sendName")
    private String sendName;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发送时间",name = "sendTime")
    private Date sendTime;

    @ApiModelProperty(value = "接收人(如果是部分发送，需要传)",name = "userCodeList")
    private List<String> userCodeList;

}
