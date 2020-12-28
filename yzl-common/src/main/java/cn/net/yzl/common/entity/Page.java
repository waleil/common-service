package cn.net.yzl.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 返回VO
 */
@Data
@ApiModel(value="Page",description="分页实体")
public class Page<T>  {
    @ApiModelProperty(value="分页参数",name="pageParam")
    private PageParam pageParam;
    @ApiModelProperty(value="数据items",name="items")
    private List<T> items;

}
