package cn.net.yzl.pm.service.impl;

import cn.net.yzl.pm.model.dto.MenuDTO;
import cn.net.yzl.pm.model.vo.RoleMenuPermissionVO;
import cn.net.yzl.pm.service.RoleMenuService;
import cn.net.yzl.pm.service.RoleService;
import cn.net.yzl.pm.entity.Role;
import cn.net.yzl.pm.entity.RoleMenu;
import cn.net.yzl.pm.exception.PmException;
import cn.net.yzl.pm.mapper.RoleMapper;
import cn.net.yzl.pm.mapper.RoleMenuMapper;
import cn.net.yzl.pm.model.dto.RoleDTO;
import cn.net.yzl.pm.model.vo.RoleMenuVO;
import cn.net.yzl.common.enums.ResponseCodeEnums;
import cn.net.yzl.common.util.YLoggerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleService roleService;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private RoleMapper roleMapper;

    /**
     * 根据角色id查询对应的菜单信息
     * @param roleId
     * @return
     */
    @Override
    public RoleMenuVO getRoleMenuListByRoleId(Integer roleId) {
        RoleMenuVO roleMenuVO = new RoleMenuVO();
        try{
            Role role = roleMapper.selectByPrimaryKey(roleId);
            if(role != null) {
                roleMenuVO.setRoleId(role.getId());//角色id
                roleMenuVO.setRoleName(role.getRoleName());//角色名称
                roleMenuVO.setRoleDesc(role.getRoleDesc());//角色描述

                List<RoleMenu> roleMenuList = roleMenuMapper.getRoleMenuListByRoleId(roleId);
                if(!CollectionUtils.isEmpty(roleMenuList)) {
                    roleMenuVO.setRoleMenuList(roleMenuList);
                }
            }
        } catch (Exception e) {
            YLoggerUtil.errorLog("exception", "根据角色id查询对应的菜单信息异常", ResponseCodeEnums.SYSTEM_ERROR_CODE.getCode(),
                    ResponseCodeEnums.SYSTEM_ERROR_CODE.getMessage(), String.valueOf(roleId), e);
            throw new PmException(ResponseCodeEnums.SYSTEM_ERROR_CODE.getCode().toString(), "根据角色id查询对应的菜单信息异常");
        }
        return roleMenuVO;
    }

    /**
     * 根据角色id查询对应的菜单id信息
     * @param roleIds
     * @return
     */
    @Override
    public List<RoleMenuPermissionVO> getRoleMenuListByRoleIds(List<Integer> roleIds) {
        return roleMenuMapper.getRoleMenuListByRoleIds(roleIds);
    }

    /**
     * 批量创建/修改角色菜单信息
     * @param roleDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int createOrUpdateRoleMenuInfoList(RoleDTO roleDTO) {
        int i = 0;
        try {
            if(roleDTO.getRole().getId() != null) {//修改
                i = roleMapper.updateByPrimaryKeySelective(roleDTO.getRole());
                if(i > 0){
                    //删除已绑定的
                    roleMenuMapper.deleteRoleMenuInfoByRoleId(roleDTO.getRole().getId());
                    i = batchCreateRoleMenuInfoList(roleDTO);
                }
            }else{//添加
                i = roleService.createRoleInfo(roleDTO.getRole());

                if(i > 0){
                    i = batchCreateRoleMenuInfoList(roleDTO);
                }
            }

        } catch (Exception e) {
            YLoggerUtil.errorLog("exception", "批量创建/修改角色菜单信息异常", ResponseCodeEnums.SYSTEM_ERROR_CODE.getCode(),
                    ResponseCodeEnums.SYSTEM_ERROR_CODE.getMessage(), String.valueOf(roleDTO), e);
            throw new PmException(ResponseCodeEnums.SYSTEM_ERROR_CODE.getCode().toString(), "批量创建/修改角色菜单信息异常");
        }
        return i;
    }

    private int batchCreateRoleMenuInfoList(RoleDTO roleDTO){
        for(RoleMenu roleMenu:roleDTO.getRoleMenuList()){
            roleMenu.setRoleId(roleDTO.getRole().getId());
            roleMenu.setCreateCode(roleDTO.getRole().getCreateCode());
        }
        return roleMenuMapper.batchCreateRoleMenuInfoList(roleDTO.getRoleMenuList());
    }

    @Override
    public MenuDTO getIsAdminByUserCodeAndMenuUrl(String userCode, String menuUrl){
        MenuDTO menuDTO = new MenuDTO();
        List<MenuDTO> list = roleMenuMapper.getIsAdminByUserCodeAndMenuPath(userCode,getMenuPath(menuUrl));
        if(list.size()>0){
            menuDTO.setMenuName(list.get(0).getMenuName());
            menuDTO.setIsAdmin(0);
            Set<Integer> set = new HashSet<>();
            for (MenuDTO dto : list) {
                set.add(dto.getIsAdmin());
            }
            if(set.contains(1)){
                menuDTO.setIsAdmin(1);
            }
        }
        return menuDTO;
    }

    private String getMenuPath(String menuUrl){
        try {
            String url = menuUrl.replace("http://", "");
            String url1= url.substring(0, url.indexOf("/"));
            String menuPath= url.replace(url1,"");
            return menuPath;
        }catch(Exception e){
            throw new PmException("传入参数格式有误");
        }
    }

}
