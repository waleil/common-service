package cn.net.yzl.common.enums;

/**
 *
 *  响应码 对应的 信息
 *
 */
public enum ResultCodeEnums {

	SUCCESS("200", "成功"), 
	SUCCESS_NODATA("201", "成功，无数据")

	;

    private String code;
	private String desc;

	private ResultCodeEnums(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static ResultCodeEnums getValueOf(String code) {
		if (null == code || "".equals(code)) {
			return null;
		}
		ResultCodeEnums[] codes = ResultCodeEnums.values();
		for (ResultCodeEnums type : codes) {
			if (type != null && type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}
}
