package cn.net.yzl.msg.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/2/7 21:29
 * @title: PersonMsgInfoSelectVo
 * @description:
 */
@Data
@ApiModel(value = "PersonMsgInfoSelectVo",description = "个人中心-消息列表查看")
public class PersonMsgInfoSelectVo {

    @ApiModelProperty(value = "消息标题",name = "title")
    private String title;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始时间",name = "startTime")
    private Date startTime;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束时间",name = "endTime")
    private Date endTime;

    @ApiModelProperty(value = "是否已读(0:未读，1:已读)",name = "isRead")
    private Integer isRead;

    @ApiModelProperty(value = "当前登陆人code",name = "userCode")
    private String userCode;

    @ApiModelProperty(value = "分页页码",name = "pageNo")
    private Integer pageNo;

    @ApiModelProperty(value = "每页显示条数",name = "pageSize")
    private Integer pageSize;

}
