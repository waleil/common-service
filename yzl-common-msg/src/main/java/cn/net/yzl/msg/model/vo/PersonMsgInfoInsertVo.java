package cn.net.yzl.msg.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/2/8 0:13
 * @title: PersonMsgInfoInsertVo
 * @description:
 */
@Data
@ApiModel(value = "PersonMsgInfoInsertVo",description = "消息发送")
public class PersonMsgInfoInsertVo {

    @ApiModelProperty(value = "消息编号",name = "msgCode")
    private String msgCode;

    @ApiModelProperty(value = "用户编号",name = "userCode")
    private String userCode;

    @ApiModelProperty(value = "创建人",name = "creator")
    private String creator;

    @ApiModelProperty(value = "所属系统",name = "systemCode")
    private Integer systemCode;

}
