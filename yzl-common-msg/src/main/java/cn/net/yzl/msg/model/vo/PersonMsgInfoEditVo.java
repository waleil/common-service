package cn.net.yzl.msg.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/2/8 0:13
 * @title: PersonMsgInfoEditVo
 * @description:
 */
@Data
@ApiModel(value = "PersonMsgInfoEditVo",description = "消息操作")
public class PersonMsgInfoEditVo {

    @ApiModelProperty(value = "消息编号",name = "msgCodeList")
    private List<String> msgCodeList;

    @ApiModelProperty(value = "用户编号",name = "userCode")
    private String userCode;

}
