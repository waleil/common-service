package cn.net.yzl.msg.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/2/23 21:00
 * @title: MsgToDoListDto
 * @description:
 */
@Data
@ApiModel(value = "MsgToDoListDto",description = "待办日历")
public class MsgToDoListDto {

    @ApiModelProperty(value = "日期",name = "date")
    private String date;

    @ApiModelProperty(value = "日期返回列表",name = "list")
    private List<MsgToDoDto> list;

}
