package cn.net.yzl.pm.mapper;

import cn.net.yzl.pm.entity.UserRole;
import cn.net.yzl.pm.model.vo.UserRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    /**
     * 根据角色id查询用户信息列表
     * @param roleId
     * @return
     */
    List<Map<String, Object>> getBiUserRoleList(@Param("roleId") Integer roleId);

    /**
     * 根据主键id删除用户信息
     * @param userRoleIds
     * @param updateCode
     * @return
     */
    int deleteBiUserRoleInfo(@Param("userRoleIds") List<Long> userRoleIds,@Param("updateCode") String updateCode);

    /**
     * 查询用户信息是否存在
     * @param userCode
     * @param roleId
     * @return
     */
    UserRole getUserRoleInfo(@Param("userCode") String userCode, @Param("roleId") Integer roleId);

    /**
     * 根据用户编号查询对应的角色信息
     * @param userCode
     * @return
     */
    List<Integer> getUserRoleListByUserCode(String userCode);

    /**
     * 批量创建用户和角色关联信息绑定
     * @param userRoleList
     * @return
     */
    int createUserRoleInfoList(@Param("list") List<UserRole> userRoleList);

    /**
     * 根据用户编号查询绑定的角色信息
     * @param userCodes
     * @return
     */
    List<UserRoleVO> getUserRoleInfoByUserCodes(@Param("userCodes") List<String> userCodes);

    /**
     * 删除用户关联角色信息
     * @param userCode
     * @return
     */
    int deleteUserRoleInfoByUserCode(String userCode);

    /**
     * 根据角色id查询用户code
     * @param roleIdList
     * @return
     */
    List<String> getUserCodesByRoleIds(@Param("roleIdList") List<Integer> roleIdList);
}