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
    NO_DATA_HAS_BEEN_UPDATE_CODE(11003,"没有数据可以被更新","没有数据可以被更新"),

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
    RESUME_CONF_HAS_EXIT_ERROR_CODE( 21113,"部门下此岗位的面试流程已存在，不可重复添加!","面试流程已存在!"),
    TRAIN_CONF_HAS_EXIT_ERROR_CODE( 21114,"部门下此岗位的培训流程已存在，不可重复添加!","培训流程已存在!"),
    DEPART_POST_EXIT_ERROR_CODE(21115,"部门下已存在对应岗位，请勿重复添加","部门下已存在对应岗位，请勿重复添加"),
    POST_EXIT_ERROR_CODE(21116,"存在同名岗位,勿重复添加","存在同名岗位,勿重复添加"),
    POST_LEVEL_EXIT_ERROR_CODE(21117,"同名岗位等级已存在,请勿重复添加","同名岗位等级已存在,请勿重复添加"),
    RESUME_STEP_USED_CODE(21118,"面试轮次字典已被使用,不可删除","面试轮次字典已被使用,不可删除"),
    POST_HAS_DEPARTPOST_ERROR_CODE(21119,"岗位已被使用,不可删除","岗位已被使用,不可删除"),
    STORE_NAME_REPEAT(21120,"仓库名称已经存在","仓库名称已经存在"),
    DEPART_POST_NOT_EXIT_ERROR_CODE(21121,"部门岗位不存在","部门岗位不存在"),
    STAFF_WORKING_ERROR_CODE(21122,"员工已在职，不可重复入职","员工已在职，不可重复入职"),
    ADJUST_FRONT_DEPART_POST_NOT_EXIST_ERROR(21123,"转出部门岗位不存在！","转出部门岗位不存在！"),
    ADJUST_LATER_DEPART_POST_NOT_EXIST_ERROR(21124,"转入部门岗位不存在！","转入部门岗位不存在！"),
    STAFF_CHANGE_CODE_NOT_EXIST_ERROR(21125,"员工变动类型不存在！","员工变动类型不存在！"),
    STAFF_RECRUIT_NOT_EXIST_ERROR(21126,"招聘任务不存在！","招聘任务不存在！"),
    STAFF_HAS_RESIGN_ERROR_CODE(21127,"该员工已是离职状态,无需重复办理!","该员工已是离职状态,无需重复办理!"),
    STAFF_HAS_POSITIVE_ERROR_CODE(21128,"该员工已是正式员工,无需重复转正!","该员工已是正式员工,无需重复转正!"),
    PROCESS_NOT_EXIST_ERROR_CODE(21129,"无对应流程信息","无对应流程信息"),
    ADJUST_FRONT_DEPART_POST_JOBNUM_ERROR(21130,"转出部门岗位在职人数错误！","转出部门岗位在职人数错误！"),
    ADJUST_LATER_DEPART_POST_JOBNUM_ERROR(21131,"转入部门岗位在职人数错误！","转入部门岗位在职人数错误！"),
    STAFF_HAS_ENTER_POST_ERROR(21132,"员工是已入岗状态，无需重复入岗！","员工是已入岗状态，无需重复入岗！"),
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
    EXCEL_HEAD_ERROR(22126,"",""),
    MEMBER_ACTION_EXIST_ERROR(21201,"顾客该综合行为已存在","顾客该综合行为已存在"),
    MEMBER_ACTION_USED_DELETE_ERROR(21202,"已有顾客绑定相应的综合行为，不可删除","已有顾客绑定相应的综合行为，不可删除"),
    MEMBER_ACTION_NOT_EXIST_ERROR(21203,"该综合行为字典不存在","该综合行为字典不存在"),
    WRITE_DISEASE_INSERT_FAIL__ERROR_CODE(21204,"手动新增病症类型失败","手动新增病症类型失败"),
    MEMBER_DISEASE_EXIST_ERROR(21205,"顾客该病症属性已存在，请勿重复添加","顾客该病症属性已存在，请勿重复添加"),
    CG_ORDER_HAS_REVIEWED(22101,"采购单已审核不可编辑","采购单已审核不可编辑"),
    CG_PRICE_LESS_THAN_ZERO(22102,"采购单价不可小于0","采购单价不可小于0"),
    CG_NUM_THAN_ZERO(22103,"采购数量不可小于0","采购数量不可小于0"),
    CG_REVIEW_EXCEPTION(22104,"采购订单未提交或者已审核","采购订单审核异常"),
    CG_WARE_NUM_EXCEPTION(22105,"入库数量超出采购数量","采购订单入库异常"),
    CG_WITHDRAW_EXCEPTION(22106,"采购订单已审核不可撤回","采购订单已审核不可撤回"),
    REPEAT_SUPPLIER_NAME_ERROR(22107,"供应商名称已存在,请重新输入","供应商名称已存在,请重新输入"),
    CG_RETURN_NUM_ERROR(22108,"退货数量超过可退数量","退货数量超过可退数量"),
    STORE_LOCAL_REPART(22109,"库位编码重复,请重新选择","库位编码重复,请重新选择"),
    REPEAT_SUPPLIER_CODE_ERROR(22110,"编码已存在,请重新输入","编码已存在,请重新输入"),
    REPEAT_STORE_NAME_ERROR(22111,"仓库名称已存在,请重新输入","仓库名称已存在,请重新输入"),
    REPEAT_STORE_CODE_ERROR(22112,"仓库编码已存在,请重新输入","仓库编码已存在,请重新输入"),
    REPEAT_STORE_STATUS_ERROR(22113,"仓库状态不允许变更","仓库状态不允许变更"),
    STORE_NO_PRODUCT(22114,"仓库没有找到商品,请采购商品","仓库没有找到该商品,请采购商品"),
    PRODUCT_STOCK_ERROR(22115,"仓库库存不足,请采购商品","仓库库存不足,请采购商品"),
    INVENTORY_ACTUALNUM_ERROR(22116,"盘点实际数量不能为空","盘点实际数量不能为空"),
    // 218 开头表示 营销活动
    // 220 开头表示 结算中心

    // 3:开头表示 ehr
    // 311  ......
    // 311 钉钉错误 代码
    DING_CONNECTION_ERROR_CODE(31100, "钉钉连接异常", "钉钉连接异常"),
    DING_ERROR_CODE(31101, "钉钉的错误代码信息", "钉钉错误代码"),
    STAFF_SOCIAL_ERROR_CODE(31102, "该薪酬范围已关联员工,不能删除!", "该薪酬范围已关联员工,不能删除!"),
    NO_DATA(31103, "当前上一级部门没有数据,继承失败", "当前上一级部门没有数据,继承失败"),
    REPEAT_DATA_ERROR_CODE(31104, "薪酬范围不能出现交叉数据", "薪酬范围不能出现交叉数据"),
    OFFICE_DATA_ERROR_CODE(31105, "物品数量不为0,不能删除", "物品数量不为0,不能删除"),
    OFFICE_ERROR_CODE(31106,"编码重复，新增失败，请重新输入编码","编码重复，新增失败，请重新输入编码"),
    OFFICE_ENCRYPT_DATA_ERROR_CODE(31107,"有重复编码，不能修改,请重新输入","有重复编码，不能修改,请重新输入"),
    RECRUIT_DATA_ERROR_CODE(31108,"有重复名称，不能修改,请重新输入","有重复名称，不能修改,请重新输入"),
    OFFICE_DATA_EXIST_CODE(31109, "已存在,请重新输入", "已存在,请重新输入"),
    PARKING_DATA_EXIST_CODE(31110,"该车牌号已有车位，申请失败","该车牌号已有车位，申请失败"),
    OFFICE_DATA_DELETE_CODE(31111, "存在该类型物品，删除失败", "存在该类型物品，删除失败"),
    REFERRAL_DATA_ERROR_CODE(31112,"该规则已关联介绍人，不能删除","该规则已关联介绍人，不能删除"),
    COURSEWARE_DATA_ERROR_CODE(31115,"存在该类型课件,不能删除","存在该类型课件,不能删除"),
    PARKING_DATA_ERROR_CODE(31113,"预留车位数大于可用车位数,不能设置","预留车位数大于可用车位数,不能设置"),
    OFFICE_DATA_UPDATE_CODE(31114,"存在该类型物品，不能修改","存在该类型物品，不能修改"),
// 4开头表示 dmc
    // 411  ......

    // 5开头表示 bi
    // 511 .....


    ;

    public void setMessage(String message) {
        this.message = message;
    }

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
