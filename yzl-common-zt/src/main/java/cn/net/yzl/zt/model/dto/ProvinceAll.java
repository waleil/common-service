package cn.net.yzl.zt.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 省市表
 */
@Data
public class ProvinceAll {

    private Integer id;//主键id

    private String name;//名称

    private Integer code;//行政区编号

    private List<CityAll> cityAllList;
}