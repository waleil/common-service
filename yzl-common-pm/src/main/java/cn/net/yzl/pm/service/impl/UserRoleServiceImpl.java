package cn.net.yzl.pm.service.impl;

import cn.net.yzl.pm.entity.UserRole;
import cn.net.yzl.pm.mapper.UserRoleMapper;
import cn.net.yzl.pm.model.dto.UserRoleDTO;
import cn.net.yzl.pm.model.vo.UserRoleVO;
import cn.net.yzl.pm.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;


    /**
     * 根据用户编号查询对应的角色信息
     * @param userCode
     * @return
     */
    @Override
    public List<Integer> getUserRoleListByUserCode(String userCode) {
        return userRoleMapper.getUserRoleListByUserCode(userCode);
    }

    /**
     * 根据角色id查询用户信息列表
     * @param roleId
     * @return
     */
    @Override
    public List<Map<String, Object>> getBiUserRoleList(Integer roleId) {
        return userRoleMapper.getBiUserRoleList(roleId);
    }

    /**
     * 根据主键id删除用户信息
     * @param userRoleIds
     * @return
     */
    @Override
    public int deleteBiUserRoleInfo(List<Long> userRoleIds,String updateCode) {
        return userRoleMapper.deleteBiUserRoleInfo(userRoleIds, updateCode);
    }

    /**
     * 查询用户信息是否存在
     * @param userCode
     * @param roleId
     * @return
     */
    @Override
    public UserRole getUserRoleInfo(String userCode, Integer roleId) {
        return userRoleMapper.getUserRoleInfo(userCode,roleId);
    }


    /**
     * 批量创建用户和角色关联信息绑定
     * @param userRoleDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int createUserRoleInfoList(UserRoleDTO userRoleDTO) {
        List<String> userCodeList = userRoleDTO.getUserCode();//用户编号集合
        List<UserRole> userRoleDTOList = userRoleDTO.getUserRoleList();//用户编号和角色集合
        int i = 0;
        if(!CollectionUtils.isEmpty(userCodeList)){
            for(String userCode:userCodeList) {
               i = userRoleMapper.deleteUserRoleInfoByUserCode(userCode);
            }
            if(!CollectionUtils.isEmpty(userRoleDTOList)) {
                userRoleMapper.createUserRoleInfoList(userRoleDTOList);
            }
        }
        return i;
    }

    /**
     * 单个创建用户和角色关联信息绑定
     * @param userRole
     * @return
     */
    @Override
    public int createUserRoleInfo(UserRole userRole) {
        return userRoleMapper.insertSelective(userRole);
    }

    /**
     * 根据用户编号查询绑定的角色信息
     * @param userCodes
     * @return
     */
    @Override
    public List<UserRoleVO> getUserRoleInfoByUserCodes(List<String> userCodes) {
        return userRoleMapper.getUserRoleInfoByUserCodes(userCodes);
    }
}
