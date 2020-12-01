package cn.net.yzl.common.entity;

import cn.net.yzl.common.enums.ResponseCodeEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 *
 * 内部方法调用的返回实体
 *
 **/

public class GeneralResult<T> implements Serializable {
    private Integer code;
    private T data;
    private String message;
    private String url;

    public static <T> GeneralResult<T> of(Integer code) {
        return new GeneralResult<T>().setCode(code);
    }

    public static <T> GeneralResult<T> of(Integer code, T data) {
        return GeneralResult.<T>of(code).setData(data);
    }

    public static <T> GeneralResult<T> success(T data) {
        return of(ResponseCodeEnums.SUCCESS_CODE.getCode(), data);
    }

    public static <T> GeneralResult<T> success() {
        return of(ResponseCodeEnums.SUCCESS_CODE.getCode());
    }

    public static <T> GeneralResult<T> errorWithMessage(Integer code, String message) {
        return new GeneralResult<T>().setCode(code).setMessage(message);
    }
    public static <T> GeneralResult<T> errorWithMessage(Integer code, String message, String url) {
        return new GeneralResult<T>().setCode(code).setMessage(message).setUrl(url);
    }
    public GeneralResult() { }

    public Integer getCode() {
        return code;
    }

    public GeneralResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public GeneralResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        if (StringUtils.isEmpty(message)){
            this.message = "";
        }
        return message;
    }

    public GeneralResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public GeneralResult<T> setUrl(String url) {
        this.url = url;
        return this;
    }
    public static <T> GeneralResult<T> invalidParam(String message) {
        return new GeneralResult<T>().setCode(ResponseCodeEnums.PARAMS_ERROR_CODE.getCode()).setMessage(message);
    }

    public boolean isSuccess() {
        return code.equals(ResponseCodeEnums.SUCCESS_CODE.getCode());
    }

    @Override
    public String toString() {
        return "GeneralResult{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
