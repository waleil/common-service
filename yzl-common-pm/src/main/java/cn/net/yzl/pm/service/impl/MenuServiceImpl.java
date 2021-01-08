package cn.net.yzl.pm.service.impl;

import cn.net.yzl.pm.mapper.UserRoleMapper;
import cn.net.yzl.pm.service.MenuService;
import cn.net.yzl.pm.entity.Menu;
import cn.net.yzl.pm.mapper.MenuMapper;
import cn.net.yzl.pm.mapper.RoleMenuMapper;
import cn.net.yzl.pm.model.vo.MenuVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        List<Integer> roleIds = userRoleMapper.getUserRoleListByUserCode(userCode);
        List<Integer> menuIds = roleMenuMapper.getRoleMenuListByRoleIds(roleIds);
        return menuMapper.getMenuListByMenuId(menuIds);
    }
}
