package cn.net.yzl.msg.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/2/7 21:35
 * @title: MsgToDoDto
 * @description:
 */
@Data
@ApiModel(value = "MsgToDoDto",description = "待办详情")
public class MsgToDoDto {

    @ApiModelProperty(value = "待办编码",name = "code")
    private String code;

    @ApiModelProperty(value = "详情",name = "detail")
    private String detail;

    @ApiModelProperty(value = "提醒时间",name = "remindTime")
    private String remindTime;

    @ApiModelProperty(value = "是否已提醒",name = "isRemind")
    private Integer isRemind;

}
