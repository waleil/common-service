package cn.net.yzl.pm.model.dto;

import cn.net.yzl.pm.entity.UserRole;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 批量添加用户信息
 */
@Data
public class UserRoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<UserRole> userRoleList;


}
