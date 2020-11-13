package cn.net.yzl.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 返回带有状态信息的对象
 *
 */
@ApiModel(value="ResultDto",description="响应结果实体")
public class ResultDto<T> implements Serializable {

	private static final long serialVersionUID = -3059930318831708557L;

	@ApiModelProperty(value="响应码,200表示成功；其他表示失败",name="code")
	private String code;

	@ApiModelProperty(value="响应信息",name="info")
	private String info;

	private T data;



	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResultDto(String code) {
		this.code = code;
		this.info = EnumResultCode.getValueOf(this.code).getDesc();
	}

	public ResultDto(String code, T data) {
		this.code = code;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
		this.info = EnumResultCode.getValueOf(this.code).getDesc();
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}




}
