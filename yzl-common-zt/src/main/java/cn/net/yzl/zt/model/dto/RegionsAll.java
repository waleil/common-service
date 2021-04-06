package cn.net.yzl.zt.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author: liuChangFu
 * @date: 2021/3/16 19:51
 * @desc: RegionsAll
 **/
@Data
public class RegionsAll {

    private String regionCode;

    private String regionName;

    private List<ProvinceAll> provinceList;

}
