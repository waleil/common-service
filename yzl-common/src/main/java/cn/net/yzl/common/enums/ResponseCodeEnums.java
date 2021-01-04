package cn.net.yzl.common.enums;

import lombok.Getter;

/**
 * 响应码 对应的 信息
 */
@Getter
public enum ResponseCodeEnums {
    SUCCESS_CODE(200, "成功", "成功"),
    NO_DATA_CODE(201, "返回数据为空", "成功，无数据"),
    ERROR(5000, "系统内部错误", "系统内部错误"),
    LOG_ERROR(5001, "日志记录错误", "日志记录错误"),
    ASPECT_ERROR(5002, "切面运行错误", "日志记录错误"),
    BEAN_OPT_ERROR(5003, "java bean 操作错误", "java bean 操作错误"),
    // 用5位标识错误代码 12100 1:服务(例如:1所在位置表示crm,ehr,dmc,bi等项目,1现在表示公共响应码)
    // 						 12:表示模块
    //						 100:表示code
    // 100开头   表示标识公共的响应码   例如:  参数错误,参数验证错误...
    // 110开头   表示认证服务的
    // 枚举名称规则  code英文描述+ERROR+CODE
    PARAMS_TYPE_ERROR_CODE(10000, "参数类型不匹配", "参数类型不匹配"),
    API_ERROR_CODE(10001, "接口调用错误", "接口调用错误"),
    SYSTEM_ERROR_CODE(10002, "系统错误", "系统错误"),
    PARAMS_EMPTY_ERROR_CODE(10003, "参数为空", "参数为空"),
    PARAMS_ERROR_CODE(10004, "参数验证错误", "参数验证错误"),
    SERVICE_ERROR_CODE(10005, "服务异常", "服务异常"),
    TOKEN_INVALID_ERROR_CODE(10006, "请登录", "token值无效"),
    TIMESTAMP_ERROR_CODE(10007, "时间戳错误", "时间戳错误"),
    JSON_PARAM_ERROR_CODE(10008, "参数JSON解析错误", "参数JSON解析错误"),
    AUTHOR_ERROR_CODE(10009, "无访问权限", "无访问权限"),
    VALIDATE_ERROR_CODE(10010, "规则校验不通过", "规则校验不通过"),
    REPEAT_ERROR_CODE(10011, "请勿重复提交", "重复提交"),
    BIZ_ERROR_CODE(10012, "业务异常", "业务逻辑错误"),
    SIGN_ERROR_CODE(10013, "签名错误", "签名错误"),
    SAVE_DATA_ERROR_CODE(10014, "数据添加异常", "数据库添加异常"),
    UPDATE_DATA_ERROR_CODE(10015, "数据更新异常", "数据更新异常"),
    MISS_REQUEST_PARAMS_CODE(10016, "用户名或密码错误", "用户名或密码错误"),
    NO_MATCHING_RESULT_CODE(10017, "无对应的数据", "无对应的数据"),
    UPLOAD_FAIL(10018, "上传失败!", "文件上传失败!"),
    UPLOAD_FORMAT_NOT_ALLOW(10019, "上传格式不支持!", "上传格式不支持!"),
    UPLOAD_SIZE_NOT_ALLOW(10019, "文件上传太大!", "文件上传太大!"),
    SAVE_FAIL(10021, "保存失败!", "保存失败!"),
    LOGIN_ERROR_CODE(11000, "访问接口参数不全", "访问接口参数不全"),
    APPID_CHECK_ERROR_CODE(11001, "appid 认证错误!", "appid 认证错误!"),
    APPID_NO_WRITE_ERROR_CODE(11002, "appid 没有可写权限!", "appid 没有可写权限!"),


    // 2开头表示 crm, 3:开头表示 ehr 4:开头表示dmc 5:开头:bi
    // 211 开头表示 员工服务
    DEPART_CHILD_ERROR_CODE(21101, "部门下存在子部门不可删除!", "部门下存在子部门不可删除"),
    DEPART_ROOT_ERROR_CODE(21102, "集团不可删除!", "集团不可删除"),
    DEPART_NOEXIT_ERROR_CODE(21103, "部门不存在!", "部门不存在"),
    POST_HAS_STAFF_ERROR_CODE(21104, "存在对应岗位的员工，不可删除该岗位!", "存在对应岗位的员工，不可删除该岗位!"),
	RESUME_EXIST_ERROR_CODE(21105, "此岗位已存在面试流程!", "此岗位面试流程已存在"),
    POST_LEVEL_HAS_STAFF_ERROR_CODE(21106, "存在对应岗位级别的员工，不可删除该岗位等级!", "存在对应岗位级别的员工，不可删除该岗位等级!"),
    DEPART_EXIT_STAFF_CODE(21107, "部门下存在员工!", "部门下存在员工"),
    STAFF_NOT_EXIT_CODE(21108, "员工不存在!", "员工不存在"),
    DEPART_ATTEND_RPITEM_EXIT_CODE(21109, "部门考勤奖惩项几日后生效数据存在!", "部门考勤奖惩项几日后生效数据存在"),
    POST_NOT_EXIT_ERROR_CODE(21110,"岗位不存在!","岗位不存在!"),
    POST_LEVEL_NOT_EXIT_ERROR_CODE( 21111,"岗位等级不存在!","岗位等级不存在!"),
    RESUME_CONF_NOT_EXIT_ERROR_CODE( 21112,"面试流程配置不存在!","面试流程配置不存在!"),
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
    // 311 钉钉错误 代码
    DING_CONNECTION_ERROR_CODE(31100, "钉钉连接异常", "钉钉连接异常"),
    DING_ERROR_CODE(31101, "钉钉的错误代码信息", "钉钉错误代码"),
    STAFF_SOCIAL_ERROR_CODE(31102, "该薪酬范围已关联员工,不能删除!", "该薪酬范围已关联员工,不能删除!"),


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
