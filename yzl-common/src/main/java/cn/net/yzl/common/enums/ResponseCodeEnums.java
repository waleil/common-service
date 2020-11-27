package cn.net.yzl.common.enums;

import lombok.Getter;

/**
 *
 *  响应码 对应的 信息
 *
 */
@Getter
public enum ResponseCodeEnums {

	SUCCESS_CODE(200, "成功","成功"),
	SUCCESS_NODATA_CODE(201, "返回数据为空","成功，无数据"),
	PARAMS_TYPE_ERROR_CODE(20000, "参数类型不匹配", "参数类型不匹配"),
	API_ERROR_CODE(20001, "接口调用错误", "接口调用错误"),
	SYSTEM_ERROR_CODE(20002, "系统错误", "系统错误"),
	PARAMS_ERROR_CODE(20003, "参数验证错误", "参数验证错误"),
	LOGIN_ERROR_CODE(20004, "用户名或密码错误", "用户名或密码错误"),
	AUTHOR_ERROR_CODE(20005, "无访问权限", "无访问权限"),
	VALIDATE_ERROR_CODE(20006, "规则校验不通过", "规则校验不通过"),
	REPEAT_ERROR_CODE(30001, "请勿重复提交", "重复提交"),
	BIZ_ERROR_CODE(30002, "业务异常", "业务逻辑错误"),
	SIGN_ERROR_CODE(50001, "签名错误", "签名错误"),
	TOKEN_ERROR_CODE(60000,"无效的token","无效的用户"),
	TOKEN_EXPIRED_CODE(60001,"用户登录时间已过期，请重新登录","用户登录时间已过期，请重新登录"),

	;

	private Integer code;
	private String message;
	private String desc;

	ResponseCodeEnums(Integer code, String message, String desc) {
		this.code = code;
		this.message = message;
		this.desc = desc;
	}

	public static ResponseCodeEnums codeOf(Integer code) {
		for (ResponseCodeEnums transferStatusEnums : values()) {
			if (transferStatusEnums.getCode().equals(code)) {
				return transferStatusEnums;
			}
		}
		return null;
	}
}
