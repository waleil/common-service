package cn.net.yzl.zt.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 城市表
 */
@Data
@Builder
public class CityAll implements Serializable {

    private Integer id;//主键id

    private String name;//城市名称

}