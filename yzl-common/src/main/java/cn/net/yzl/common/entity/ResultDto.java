package cn.net.yzl.common.entity;

import cn.net.yzl.common.enums.ResponseCodeEnums;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一的返回实体
 **/
@Data
@ApiModel(value="ResultDto",description="响应结果实体")
public class ResultDto<T> implements Serializable {

    @ApiModelProperty(value="响应状态(1:成功,2:失败)",name="status")
    private Integer status;
    @ApiModelProperty(value="响应码,200表示成功；其他表示失败",name="code")
    private Integer code;
    @ApiModelProperty(value="响应信息",name="info")
    private String message;
    private T data;

    /**
     * 成功状态
     */
    public static final int SUCCESS_STATUS = 1;
    /**
     * 默认错误状态
     */
    public static final int ERROR_STATUS = 0;


    public static <T> ResultDto<T> success() {
        return success(ResponseCodeEnums.SUCCESS_CODE.getCode(),SUCCESS_STATUS,null,null);
    }

    public static <T> ResultDto<T> success(T data) {
        return success(ResponseCodeEnums.SUCCESS_CODE.getCode(),SUCCESS_STATUS,null,data);
    }

    public static <T> ResultDto<T> success(Integer code, Integer status, String message, T data){
        return new ResultDto<T>().setData(data).setStatus(status).setCode(code).setMessage(message);
    }

    public static <T> ResultDto<T> nodata() {
        return nodata(ResponseCodeEnums.NO_DATA_CODE.getCode(),SUCCESS_STATUS,ResponseCodeEnums.NO_DATA_CODE.getMessage());
    }

    public static <T> ResultDto<T> nodata(String message) {
        return nodata(ResponseCodeEnums.NO_DATA_CODE.getCode(),SUCCESS_STATUS,message);
    }
    public static <T> ResultDto<T> nodata(Integer code, Integer status, String message) {
        return new ResultDto<T>().setStatus(status).setCode(code).setMessage(message);
    }

    public static <T> ResultDto<T> fail(Integer code, String message) {
        return fail(code,ERROR_STATUS,message,null);
    }

    public static <T> ResultDto<T> fail(Integer code, String message, T data) {
        return fail(code,ERROR_STATUS,message,data);
    }

    public static <T> ResultDto<T> fail(Integer code, Integer status, String message, T data) {
        return new ResultDto<T>().setStatus(status).setCode(code).setMessage(message).setData(data);
    }

    public ResultDto<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public ResultDto<T> setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public ResultDto<T> setData(T data) {
        this.data = data;
        return this;
    }

    public ResultDto<T> setMessage(String message) {
        this.message = message;
        return this;
    }
}
