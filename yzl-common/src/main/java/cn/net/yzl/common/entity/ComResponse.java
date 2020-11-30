package cn.net.yzl.common.entity;

import cn.net.yzl.common.enums.ResponseCodeEnums;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一的返回实体
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComResponse<T> implements Serializable {

    /**
     * 响应状态
     */
    private Integer status;
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    /**
     * 成功状态
     */
    public static final int SUCCESS_STATUS = 1;
    /**
     * 默认错误状态
     */
    public static final int ERROR_STATUS = 0;


    public static <T> ComResponse<T> success() {
        return success(ResponseCodeEnums.SUCCESS_CODE.getCode(),SUCCESS_STATUS,null,null);
    }

    public static <T> ComResponse<T> success(T data) {
        return success(ResponseCodeEnums.SUCCESS_CODE.getCode(),SUCCESS_STATUS,null,data);
    }

    public static <T> ComResponse<T> success(Integer code, Integer status, String message, T data){
        return new ComResponse<T>().setData(data).setStatus(status).setCode(code).setMessage(message);
    }

    public static <T> ComResponse<T> nodata() {
        return nodata(ResponseCodeEnums.NO_DATA_CODE.getCode(),SUCCESS_STATUS,ResponseCodeEnums.NO_DATA_CODE.getMessage());
    }

    public static <T> ComResponse<T> nodata(String message) {
        return nodata(ResponseCodeEnums.NO_DATA_CODE.getCode(),SUCCESS_STATUS,message);
    }
    public static <T> ComResponse<T> nodata(Integer code, Integer status, String message) {
        return new ComResponse<T>().setStatus(status).setCode(code).setMessage(message);
    }

    public static <T> ComResponse<T> fail(Integer code, String message) {
        return fail(code,ERROR_STATUS,message,null);
    }

    public static <T> ComResponse<T> fail(Integer code, String message, T data) {
        return fail(code,ERROR_STATUS,message,data);
    }

    public static <T> ComResponse<T> fail(Integer code, Integer status, String message, T data) {
        return new ComResponse<T>().setStatus(status).setCode(code).setMessage(message).setData(data);
    }

    public ComResponse<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public ComResponse<T> setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public ComResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public ComResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }
}
