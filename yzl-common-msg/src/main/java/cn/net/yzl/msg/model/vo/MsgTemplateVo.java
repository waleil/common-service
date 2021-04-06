package cn.net.yzl.msg.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * cn.net.yzl.msg.model.pojo
 * 2021/2/25 13:44
 *
 * @author yangxiaopeng
 */
@Data
public class MsgTemplateVo {


    @ApiModelProperty(value = "模版编号", name = "code")
    private String code;

    @ApiModelProperty(value = "系统类型(1：crm，2：ehr，3：dmc，4：bi)", name = "systemCode")
    private Integer systemCode;

    @ApiModelProperty(value = "模版类型", name = "type")
    private Integer type;

    @ApiModelProperty(value = "模版标题", name = "title")
    private String title;

    @ApiModelProperty(value = "发送人姓名", name = "sendName")
    private String sendName;

//    @ApiModelProperty(value = "模板内容", name = "code")
//    private String content;

    @ApiModelProperty(value = "接收人编号", name = "userCode")
    private String userCode;

    @ApiModelProperty(value = "发送人编号", name = "creator")
    private String creator;


    @ApiModelProperty(value = "模板参数", name = "params")
    private Object[] params;


}
