package cn.net.yzl.common.exception;

import lombok.Data;

@Data
public class CommonException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // 错误码
    private Integer code;

    // 错误信息
    private String msg;

    public CommonException(java.lang.Exception e) {
        super(e);
    }

    public CommonException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
