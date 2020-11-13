package cn.net.yzl.common;

/**
 *
 *  响应码 对应的 信息
 *
 */
public enum EnumResultCode {

	SUCCESS("200", "成功"), 
	SUCCESS_NODATA("201", "成功，无数据")

	;

    private String code;
	private String desc;

	private EnumResultCode(String code, String desc) {
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

	public static EnumResultCode getValueOf(String code) {
		if (null == code || "".equals(code)) {
			return null;
		}
		EnumResultCode[] codes = EnumResultCode.values();
		for (EnumResultCode type : codes) {
			if (type != null && type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}
}
