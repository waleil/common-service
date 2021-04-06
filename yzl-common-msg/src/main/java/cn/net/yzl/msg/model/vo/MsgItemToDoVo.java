package cn.net.yzl.msg.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/2/24 1:14
 * @title: MsgItemToDoVo
 * @description:
 */
@Data
public class MsgItemToDoVo {

    @ApiModelProperty(value = "待办code", name = "code")
    private String code;

    @ApiModelProperty(value = "待办类型", name = "type")
    private Integer type;

    @ApiModelProperty(value = "系统类型（1：crm，2：ehr，3：dmc，4：bi）", name = "systemCode")
    private Integer systemCode;

    @ApiModelProperty(value = "操作人", name = "oprUser")
    private String oprUser;

}
