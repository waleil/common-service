package cn.net.yzl.zt.model.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 添加用户信息
 * @authror renjudong
 * @date 2020/12/7 14:11
 */
@Data
public class BiUserRoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "userId cant be null")
    private Long userId;//用户ID
    private String userName;//用户名称
    @NotNull(message = "roleId cant be null")
    private Integer roleId;//角色ID
    @NotNull(message = "deptId cant be null")
    private Integer deptId;//部门ID
    private String deptName;//部门名称
    private String createCode;//创建人编号


}
