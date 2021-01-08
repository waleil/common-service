package cn.net.yzl.pm.service;

import cn.net.yzl.pm.entity.Menu;
import cn.net.yzl.pm.model.vo.MenuVO;

import java.util.List;

public interface MenuService {

    /**
     * 根据菜单id查询对应的菜单信息
     * @param userCode
     * @return
     */
    List<MenuVO> getMenuListByMenuId(String userCode);

    /**
     * 查询菜单列表
     * @return
     */
    List<Menu> getMenuList();

}
