package cn.net.yzl.msg.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/2/22 23:22
 * @title: MsgToDoUpdateVo
 * @description:
 */
@Data
@ApiModel(value = "MsgToDoUpdateVo",description = "待办修改入参")
public class MsgToDoUpdateVo {

    @ApiModelProperty(value = "待办code",name = "code")
    private String code;

    @ApiModelProperty(value = "提醒时间",name = "remindTime")
    private String remindTime;

    @ApiModelProperty(value = "详情",name = "detail")
    private String detail;

    @ApiModelProperty(value = "操作人",name = "updator")
    private String updator;

}
