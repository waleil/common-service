package cn.net.yzl.operatelogger.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName OperateLogModel
 * @Descripion 用户行为日志类
 * @Author liwenjie
 * @Date 2020/12/10 9:57
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OperateLogModel {
    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    private Long id;

    /**
     * 操作模块
     */
    private String operModule;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     * 具体见BusinessType枚举
     * 可视业务系统业务自定义枚举类
     */
    private Integer operType;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 操作人员名称
     */
    private String operUserName;

    /**
     * 操作人员id
     */
    private Long operUserId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 请求url
     */
    private String operUrl;

    /**
     * 操作地址
     */
    private String operIp;

    /**
     * 请求参数
     */
    private String operParam;

    /**
     * 返回值
     */
    private String jsonResult;

    /**
     * 操作状态（1正常 0异常）
     * 具体见BusinessStatus枚举
     */
    private Integer status;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 操作时间
     */
    private Date operTime;

}

