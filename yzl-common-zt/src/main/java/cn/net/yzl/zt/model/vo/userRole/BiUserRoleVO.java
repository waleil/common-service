package cn.net.yzl.zt.model.vo.userRole;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @authror renjudong
 * @date 2020/12/7 14:11
 */
@Data
public class BiUserRoleVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userRoleId;//主键id
    private Long userId;//用户ID
    private String userName;//用户名称
    private Integer roleId;//角色ID
    private Integer deptId;//部门ID
    private String deptName;//部门名称
    private Integer isDel;//是否删除 (1:是 0:否)
    private Integer delSource;//删除来源 (1:BI 2:EHR)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;//创建时间
    private String createCode;//创建人编号


}
