package cn.net.yzl.pm.model.dto;

import cn.net.yzl.pm.entity.UserRole;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 批量添加用户和角色关联信息
 */
@Data
public class UserRoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Valid
    @NotNull
    private List<UserRole> userRoleList;

    private List<String> userCodeList;

}
