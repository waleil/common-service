package cn.net.yzl.msg.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * cn.net.yzl.staff.vo.msg
 * 2021/2/5 14:50
 *
 * @author yangxiaopeng
 */
@Data
@Component
@ApiModel(value = "MsgInfoPageVo", description = "分页消息接收参数")
public class MsgInfoPageVo {
    @ApiModelProperty("消息标题")
    private String title;
    @ApiModelProperty("消息类别编号")
    private String typeCode;
    private Integer pageNo;
    private Integer pageSize;
    @ApiModelProperty("当前用户编号")
    private String userNo;
    @ApiModelProperty("开始时间")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    @ApiModelProperty("结束时间")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
