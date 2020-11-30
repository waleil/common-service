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
	NO_DATA_CODE(201, "返回数据为空","成功，无数据"),
	// 用5位标识错误代码 12100 1:服务(例如:1所在位置表示crm,ehr,dmc,bi等项目,1现在表示公共响应码)
	// 						 12:表示模块
	//						 100:表示code
	// 100开头   表示标识公共的响应码   例如:  参数错误,参数验证错误...
	// 110开头   表示认证服务的
	// 枚举名称规则  code英文描述+ERROR+CODE
	PARAMS_TYPE_ERROR_CODE(10000, "参数类型不匹配", "参数类型不匹配"),
	API_ERROR_CODE(10001, "接口调用错误", "接口调用错误"),
	SYSTEM_ERROR_CODE(10002, "系统错误", "系统错误"),
	PARAMS_EMPTY_ERROR_CODE(10003, "参数为空","参数为空"),
	PARAMS_ERROR_CODE(10004, "参数验证错误", "参数验证错误"),
	SERVICE_ERROR_CODE(10005, "服务异常","服务异常"),
	TOKEN_INVALID_ERROR_CODE(10006, "token值无效","token值无效"),
	TIMESTAMP_ERROR_CODE(10007, "时间戳错误","时间戳错误"),
	JSON_PARAM_ERROR_CODE(10008,"参数JSON解析错误","参数JSON解析错误"),
	AUTHOR_ERROR_CODE(10009, "无访问权限", "无访问权限"),
	VALIDATE_ERROR_CODE(10010, "规则校验不通过", "规则校验不通过"),
	REPEAT_ERROR_CODE(10011, "请勿重复提交", "重复提交"),
	BIZ_ERROR_CODE(10012, "业务异常", "业务逻辑错误"),
	SIGN_ERROR_CODE(10013, "签名错误", "签名错误"),
	// 2开头表示 crm, 3:开头表示 ehr 4:开头表示dmc 5:开头:bi
	// 211 开头表示 员工服务
	// 212 开头表示 顾客服务
	// 213 开头表示 商品服务
	// 214 开头表示 媒介服务
	// 215 开头表示 订单服务
	// 216 开头表示 物流服务
	// 217 开头表示 优惠服务
	// 218 开头表示 员工商城服务
	// 219 开头表示 工单服务
	// 220 开头表示 质检服务
	// 221 开头表示 仓储中心
	// 218 开头表示 营销活动
	// 220 开头表示 结算中心

	// 3:开头表示 ehr
	// 311  ......

	// 4开头表示 dmc
	// 411  ......

	// 5开头表示 bi
	// 511 .....










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
