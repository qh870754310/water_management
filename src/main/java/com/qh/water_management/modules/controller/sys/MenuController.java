package com.qh.water_management.modules.controller.sys;

import com.qh.water_management.modules.common.controller.BaseController;
import com.qh.water_management.modules.common.utils.BeanHelper;
import com.qh.water_management.modules.common.utils.Result;
import com.qh.water_management.modules.entity.sys.ResourceManagement;
import com.qh.water_management.modules.service.sys.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/11/20 10:09
 * @Description: 资源管理
 */
@Controller
@RequestMapping("/system/menu/")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @GetMapping(value = "index")
    public String index(Integer levelId) {
        return "vue/sys/menu/list";
    }

    @RequestMapping(value = "getListByLevelId")
    @ResponseBody
    public Object getListByLevelId(Integer levelId) {
        List<Map> list = menuService.getListByLevelId(levelId);
        return list;
    }

    @RequestMapping(value = "getListByPid")
    @ResponseBody
    public Object getListByPid(String parentId) {
        List<Map> list = menuService.getListByPid(parentId);
        return list;
    }

    @RequestMapping(value = "edit")
    public String edit() {
        return "vue/sys/menu/add";
    }

    @RequestMapping(value = "editRoot")
    public String editRoot() {
        return "vue/sys/menu/editRoot";
    }

    @RequestMapping(value = "getFatherIds")
    @ResponseBody
    public String getFatherIds(String uuid) {
        return menuService.getFatherIds(uuid);
    }

    @RequestMapping(value = "save")
    @ResponseBody
    public Result save(ResourceManagement resourceManagement) {
        try {
            menuService.save(resourceManagement);
            return Result.ok("保存成功！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("保存失败！").put("title", "操作提示");
        }
    }

    @RequestMapping(value = "getDetailByUuid")
    @ResponseBody
    public Map<String, Object> getDetailByUuid(String uuid) {
        return menuService.getDetailByUuid(uuid);
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public Result update(@RequestParam Map<String, Object> params) {
        try {
            ResourceManagement resourceManagement = (ResourceManagement) BeanHelper.mapToObject_1(params, ResourceManagement.class);
            menuService.update(resourceManagement);
            return Result.ok("更新成功！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新失败！").put("title", "操作提示");
        }
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public Result delete(String id) {
        try {
            String[] uuids = id.split(",");
            menuService.delete(uuids);
            return Result.ok("删除成功！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败！").put("title", "操作提示");
        }
    }

    @RequestMapping(value = "functionMenuAdd")
    @ResponseBody
    public Result functionMenuAdd(String url) {
        try {
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("未知错误");
        }
    }

    @RequestMapping(value = "functionMenuSave")
    @ResponseBody
    public Result functionMenuSave() {
        try {
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("未知错误");
        }
    }
}
