package cn.net.yzl.pm.service.impl;

import cn.net.yzl.pm.mapper.UserRoleMapper;
import cn.net.yzl.pm.model.vo.RoleMenuPermissionVO;
import cn.net.yzl.pm.service.MenuService;
import cn.net.yzl.pm.entity.Menu;
import cn.net.yzl.pm.mapper.MenuMapper;
import cn.net.yzl.pm.mapper.RoleMenuMapper;
import cn.net.yzl.pm.model.vo.MenuVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;

    /**
     * 查询菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenuList() {
        return menuMapper.getMenuList();
    }

    @Override
    public List<MenuVO> getMenuListByMenuId(String userCode) {
        List<MenuVO> menuVOList = new ArrayList<>();
        //查询用户角色
        List<Integer> roleIds = userRoleMapper.getUserRoleListByUserCode(userCode);
        if (!CollectionUtils.isEmpty(roleIds)) {
            //根据角色查菜单
            List<RoleMenuPermissionVO> roleMenuPermissionListByRoleIds = roleMenuMapper.getRoleMenuListByRoleIds(roleIds);
            if (!CollectionUtils.isEmpty(roleMenuPermissionListByRoleIds)) {
                List<Integer> menuIdList = roleMenuPermissionListByRoleIds.stream().map(RoleMenuPermissionVO::getMenuId).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(menuIdList)) {
                    //根据菜单id查询对应的菜单信息
                    menuVOList = menuMapper.getMenuListByMenuId(menuIdList);
                    if (!CollectionUtils.isEmpty(menuVOList)) {
                        menuVOList.stream()
                                .map(menuVO -> roleMenuPermissionListByRoleIds.stream()
                                        .filter(roleMenuPermission -> menuVO.getMenuId().equals(roleMenuPermission.getMenuId()))
                                        .findFirst()
                                        .map(roleMenuPermission -> {
                                            menuVO.setIsEdit(roleMenuPermission.getIsEdit());
                                            menuVO.setIsLook(roleMenuPermission.getIsLook());
                                            menuVO.setIsAdmin(roleMenuPermission.getIsAdmin());
                                            return menuVO;
                                        })
                                ).collect(Collectors.toList());
                        if (!CollectionUtils.isEmpty(menuVOList)) {
                            menuVOList.removeIf(e -> (e.getIsLook() == 0 && e.getIsEdit() == 0 && e.getIsAdmin() == 0));
                        }
                    }
                }
            }
        }
        return menuVOList;
    }
}
