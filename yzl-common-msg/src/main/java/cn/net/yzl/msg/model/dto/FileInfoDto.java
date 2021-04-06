package cn.net.yzl.msg.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/3/24 22:11
 * @title: FileDto
 * @description:
 */
@Data
@ApiModel(value = "FileDto",description = "附件")
public class FileInfoDto {

    @ApiModelProperty(value = "文件名称",name = "name")
    private String name;

    @ApiModelProperty(value = "文件地址",name = "url")
    private String url;

}
