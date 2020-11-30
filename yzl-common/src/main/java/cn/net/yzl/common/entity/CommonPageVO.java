package cn.net.yzl.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * 返回VO
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonPageVO<T>  {

    private PageParam pageParam;
    private List<T> items;
    private Integer totalCount;

}
