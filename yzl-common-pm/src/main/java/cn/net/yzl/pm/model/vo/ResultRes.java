package cn.net.yzl.pm.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * API返回统一基类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultRes implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer status;
	private Integer code;
	private String error;
	private String msg;
	private String message;
	private Object data;

	private Integer statusUp;
	private Integer stateUP;
	private Integer codeUp;
	private String msgUp;
	private String messageUp;
	private String errorMsgUp;
	private Object dataUp;
}


