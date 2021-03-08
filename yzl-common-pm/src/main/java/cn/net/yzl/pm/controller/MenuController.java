package cn.net.yzl.pm.controller;

import cn.net.yzl.pm.entity.Menu;
import cn.net.yzl.pm.model.vo.MenuVO;
import cn.net.yzl.pm.service.MenuService;
import cn.net.yzl.common.entity.ComResponse;
import cn.net.yzl.common.entity.Page;
import cn.net.yzl.common.util.AssemblerResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "角色权限-菜单管理")
@RestController
@RequestMapping("/menu")
public class MenuController {


    @Autowired
    private MenuService menuService;

    /**
     * 查询菜单列表
     * @return
     */
    @ApiOperation("查询菜单列表")
    @RequestMapping(value = "/getMenuList", method = RequestMethod.GET)
    public ComResponse getMenuList() {

        List<Menu> menuList = menuService.getMenuList();
        Page<Menu> commonPageVO = AssemblerResultUtil.resultAssemblerSingleList(menuList);
        return ComResponse.success(commonPageVO);

    }

    /**
     * 根据员工编号查询对应的菜单信息
     * @param userCode
     * @return
     */
    @ApiOperation("根据员工编号查询对应的菜单信息")
    @RequestMapping(value = "/getMenuListByUserCode", method = RequestMethod.GET)
    public ComResponse getMenuListByUserCode(@RequestParam String userCode) {

        //查询appId对应的appKey是否存在
/*        AppRelation appRelationInfo = appRelationService.getAppRelationInfo(appId);
        if (appRelationInfo == null || !StringUtils.hasText(appRelationInfo.getAppKey())) {
            return ComResponse.fail(ResponseCodeEnums.AUTHOR_ERROR_CODE.getCode(), "无访问权限");
        }
        String signText = (time + userCode + appId + "$" + appRelationInfo.getAppKey()).toLowerCase();
        YLoggerUtil.infoLog("getMenuListByUserCode signText", signText);
        String newSign = XSecurityUtil.encodeByMD5(signText);
        if (!Objects.equals(sign, newSign)) {
            return ComResponse.fail(ResponseCodeEnums.SIGN_ERROR_CODE.getCode(), "签名不匹配");
        }*/
        List<MenuVO> menuList = menuService.getMenuListByMenuId(userCode);
        Page<MenuVO> commonPageVO = AssemblerResultUtil.resultAssembler(menuList);
        return ComResponse.success(commonPageVO);

    }


}
