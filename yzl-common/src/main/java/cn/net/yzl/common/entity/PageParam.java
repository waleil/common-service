package cn.net.yzl.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统一的分页实体
 **/
@Data
@ApiModel(value="PageParam",description="响应结果实体")
public class PageParam {



	@ApiModelProperty(value="下一页",name="nextPage",hidden = true)
	private int nextPage;
	@ApiModelProperty(value="上一页",name="previousPage",hidden = true)
	private int previousPage;
	@ApiModelProperty(value="每页的数量",name="pageSize",hidden = true)
	private int pageSize = 10;
	@ApiModelProperty(value="当前页",name="pageNo",hidden = true)
	private int pageNo = 1;
	@ApiModelProperty(value="总页数",name="pageTotal",hidden = true)
	private int pageTotal;
	@ApiModelProperty(value="总条数",name="totalCount",hidden = true)
	private int totalCount;

}
