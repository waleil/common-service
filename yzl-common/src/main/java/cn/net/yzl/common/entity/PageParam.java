package cn.net.yzl.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一的分页实体
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageParam {
	/**
	 * 每页多少条
	 */
	@JsonProperty("pageSize")
	private Integer pageSize = 10;

	/**
	 * 当前页码
	 */
	@JsonProperty("pageNo")
	private Integer pageNo = 1;

	/**
	 * 总页数
	 */
	@JsonProperty("pageTotal")
	private Integer pageTotal;

	/**
	 * 总条数
	 */
	@JsonProperty("totalCount")
	private Integer totalCount;

}
