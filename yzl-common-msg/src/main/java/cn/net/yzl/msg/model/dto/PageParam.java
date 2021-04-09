package cn.net.yzl.msg.model.dto;

import lombok.Data;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/4/8 21:13
 * @title: PageParam
 * @description:
 */
@Data
public class PageParam {

    private int nextPage;
    private int pageNo;
    private int pageSize;
    private int pageTotal;
    private int totalCount;

}
