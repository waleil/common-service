package cn.net.yzl.msg.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author wujianing
 * @version 1.0
 * @date: 2021/4/8 21:12
 * @title: PageInfo
 * @description:
 */
@Data
public class PageInfo {

    private List<PartStaff> items;
    private PageParam pageParam;

}
