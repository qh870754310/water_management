package com.qh.water_management.modules.controller.sys;

import com.qh.water_management.modules.common.utils.Result;
import com.qh.water_management.modules.common.utils.ShiroUtils;
import com.qh.water_management.modules.entity.sys.ResourceManagement;
import com.qh.water_management.modules.service.sys.AuthAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/10/26 9:59
 * @Description:
 */
@RestController
@RequestMapping("/system/authAccess/")
public class AuthAccessController {

    @Autowired
    private AuthAccessService authAccessService;

    /**
     * 查询顶级菜单
     */
    @GetMapping(value = "getTopMenuList")
    public Result getTopMenuList() {
        List<ResourceManagement> resourceManagements = authAccessService.getTopMenuList(ShiroUtils.getUserId());
        return Result.ok().put("menuList", resourceManagements);
    }

    /**
     * 根据菜单id获取菜单信息
     * @param menuId
     * @param codeSetId
     * @return
     */
    @RequestMapping(value = "/getMenuListByRoleIdAndCodeSetId")
    public Result getMenuListByRoleIdAndCodeSetId(String menuId, String codeSetId) {
        Map<String, Object> params = new HashMap<>();
        params.put("menuId", menuId);
        params.put("codeSetId", codeSetId);
        List<ResourceManagement> resourceManagements = authAccessService.getMenuListByRoleIdAndCodeSetId(params, ShiroUtils.getUserId());
        return Result.ok().put("menus", resourceManagements);
    }

    /**
     *
     * @param parentId
     */
    @GetMapping(value = "getMenuListByPid")
    public Result getMenuListByPid(String parentId) {
        Map<String, Object> params = new HashMap<>();
        params.put("parentId", parentId);
        List<ResourceManagement> resourceManagements = authAccessService.getMenuListByPid(params, ShiroUtils.getUserId());
        return Result.ok().put("menuChildren", resourceManagements);
    }

    @RequestMapping(value = "getListByRoleId")
    @ResponseBody
    public Object getListByRoleId(@RequestParam Map<String, Object> params) {
        return authAccessService.getListByRoleId(params);
    }

    @RequestMapping(value = "getListByRoleIdAndParentId")
    @ResponseBody
    public Object getListByRoleIdAndParentId(@RequestParam Map<String, Object> params) {
        return authAccessService.getListByRoleIdAndParentId(params);
    }

    @RequestMapping(value = "syncMenu")
    @ResponseBody
    public Result syncMenu(@RequestParam Map<String, Object> params) {
        try {
            authAccessService.syncMenu(params);
            return Result.ok("菜单已同步！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("未知错误").put("title", "操作提示");
        }
    }

    /**
     * 授权
     * @param params
     * @return
     */
    @RequestMapping(value = "grant")
    @ResponseBody
    public Result grant(@RequestParam Map<String, Object> params) {
        try {
            List<Object> list = authAccessService.grant(params);
            return Result.ok("操作成功").put("title", "操作提示").put("data", list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("授权失败").put("title", "操作提示");
        }
    }
}
