package cn.net.yzl.zt.model.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO {
    @NotNull(message = "userNo cant be null")
    private String userNo;//用户编号

    @NotNull(message = "passWord cant be null")
    private String passWord;//用户密码
}
