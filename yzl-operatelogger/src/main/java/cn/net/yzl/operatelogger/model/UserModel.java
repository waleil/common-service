package cn.net.yzl.operatelogger.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserModel
 * @Descripion 用户类
 * @Author liwenjie
 * @Date 2020/12/10 9:57
 **/
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    /**
     * 用户id
     */
    private Long userNo;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 部门名称
     */
    private String deptName;
}
