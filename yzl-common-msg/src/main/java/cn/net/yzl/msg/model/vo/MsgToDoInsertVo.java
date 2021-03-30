package cn.net.yzl.msg.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/2/7 9:50
 * @title: MsgToDoInsertVo
 * @description:
 */
@Data
@ApiModel(value = "MsgToDoInsertVo",description = "待办新增入参")
public class MsgToDoInsertVo {

    @ApiModelProperty(value = "待办编号",name = "code")
    private String code;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "提醒时间",name = "remindTime")
    private Date remindTime;

    @ApiModelProperty(value = "详情",name = "detail")
    private String detail;

    @ApiModelProperty(value = "创建人",name = "createUser")
    private String createUser;

}
