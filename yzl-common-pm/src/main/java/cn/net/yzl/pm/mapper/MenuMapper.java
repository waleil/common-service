package cn.net.yzl.pm.mapper;

import cn.net.yzl.pm.entity.Menu;
import cn.net.yzl.pm.model.vo.MenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    /**
     * 根据菜单id查询对应的菜单信息
     * @param menuIds
     * @return
     */
    List<MenuVO> getMenuListByMenuId(@Param("menuIds") List<Integer> menuIds);

    /**
     * 查询菜单列表
     * @return
     */
    List<Menu> getMenuList();
}