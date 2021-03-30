package cn.net.yzl.msg.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * cn.net.yzl.staff.pojo.msg
 * 2021/2/5 13:50
 *
 * @author yangxiaopeng
 */
@Slf4j
@Data
public class MsgInfoPo implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "消息编号", name = "code")
    private String code;

    @ApiModelProperty(value = "系统类型(1：crm，2：ehr，3：dmc，4：bi)", name = "systemCode")
    private Integer systemCode;

    @ApiModelProperty(value = "消息标题", name = "title")
    private String title;

    @ApiModelProperty(value = "消息内容", name = "content")
    private String content;

    @ApiModelProperty(value = "关联文件", name = "file_url")
    private String fileUrl;


    @ApiModelProperty(value = "发送方式（1：实时发送，2：定时发送）", name = "send_mode")
    private Integer sendMode;

    @ApiModelProperty(value = "类型编号", name = "type_code")
    private String typeCode;

    @ApiModelProperty(value = "通知方式（默认0：系统消息）",name = "noticeMode")
    private Integer noticeMode;

    @ApiModelProperty(value = "类型名称", name = "name")
    private String name;

    @ApiModelProperty(value = "消息状态（0：未读，1：部分已读，2：全员已读）", name = "msg_status")
    private Integer msgStatus;

    @ApiModelProperty(value = "发送状态（0：待发送，1：已发送，2：取消发送）", name = "send_status")
    private Integer sendStatus;

    @ApiModelProperty(value = "发送对象（默认0：全部员工，1：部分员工）", name = "target")
    private Integer target;

    @ApiModelProperty(value = "部分员工编号", name = "target_list")
    private String targetList;

    @ApiModelProperty(value = "发送人编号", name = "send_user")
    private String sendUser;

    @ApiModelProperty(value = "发送人姓名", name = "send_user")
    private String sendName;

    @ApiModelProperty(value="发送时间")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date sendTime;


    @ApiModelProperty(value = "是否删除（默认0：不删除，1：已删除）", name = "is_del")
    private Integer isDel;


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










}
