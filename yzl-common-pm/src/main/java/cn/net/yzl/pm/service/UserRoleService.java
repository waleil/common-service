package cn.net.yzl.pm.service;

import cn.net.yzl.pm.entity.UserRole;
import cn.net.yzl.pm.model.vo.UserRoleVO;

import java.util.List;
import java.util.Map;

public interface UserRoleService {

    /**
     * 根据用户编号查询对应的角色信息
     * @param userCode
     * @return
     */
    List<Integer> getUserRoleListByUserCode(String userCode);

    /**
     * 根据角色id查询用户信息列表
     * @param roleId
     * @return
     */
    List<Map<String, Object>> getBiUserRoleList(Integer roleId);

    /**
     * 根据主键id删除用户信息
     * @param userRoleIds
     * @param updateCode
     * @return
     */
    int deleteBiUserRoleInfo(List<Long> userRoleIds,String updateCode);

    /**
     * 查询用户信息是否存在
     * @param userCode
     * @param roleId
     * @return
     */
    UserRole getUserRoleInfo(String userCode, Integer roleId);

    /**
     * 批量创建用户和角色关联信息绑定
     * @param userRoleDTOList
     * @return
     */
    int createUserRoleInfoList(List<UserRole> userRoleDTOList);

    /**
     * 单个创建用户和角色关联信息绑定
     * @param userRole
     * @return
     */
    int createUserRoleInfo(UserRole userRole);

    /**
     * 根据用户编号查询绑定的角色信息
     * @param userCodes
     * @return
     */
    List<UserRoleVO> getUserRoleInfoByUserCodes(List<String> userCodes);

}
