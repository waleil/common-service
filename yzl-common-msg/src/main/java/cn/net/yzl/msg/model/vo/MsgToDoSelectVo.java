package cn.net.yzl.msg.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/2/22 23:22
 * @title: MsgToDoSelectVo
 * @description:
 */
@Data
@ApiModel(value = "MsgToDoSelectVo",description = "待办查询入参")
public class MsgToDoSelectVo {

    @ApiModelProperty(value = "日期（yyyy-MM）",name = "date")
    private String date;

    @ApiModelProperty(value = "操作人",name = "userCode")
    private String userCode;

    @ApiModelProperty(value = "分页页码",name = "pageNo")
    private Integer pageNo;

    @ApiModelProperty(value = "每页显示条数",name = "pageSize")
    private Integer pageSize;

}
