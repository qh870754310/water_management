package com.qh.water_management.modules.controller.sys;

import com.qh.water_management.modules.common.utils.Result;
import com.qh.water_management.modules.entity.sys.AuthData;
import com.qh.water_management.modules.service.sys.SysAuthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/12/12 14:00
 * @Description: 数据权限
 */
@Controller
@RequestMapping(value = "/system/sysAuthData/")
public class SysAuthDataController {

    @Autowired
    private SysAuthDataService sysAuthDataService;

    @RequestMapping(value = "index")
    public String index() {
        return "vue/sys/sysAuthData/index";
    }

    @RequestMapping(value = "edit")
    public String edit() {
        return "vue/sys/sysAuthData/edit";
    }

    @RequestMapping(value = "getListByLevelId")
    @ResponseBody
    public Object getListByLevelId(Integer levelId) {
        return sysAuthDataService.getListByLevelId(levelId);
    }

    @RequestMapping(value = "getListByParentId")
    @ResponseBody
    public Object getListByParentId(String parentId) {
        return sysAuthDataService.getListByParentId(parentId);
    }

    @RequestMapping(value = "getDetailByUuid")
    @ResponseBody
    public Object getDetailByUuid(String uuid) {
        return sysAuthDataService.getDetailByUuid(uuid);
    }

    @RequestMapping(value = "save")
    @ResponseBody
    public Result save(AuthData authData) {
        try {
            sysAuthDataService.save(authData);
            return Result.ok("保存成功！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("保存失败！").put("title", "操作提示");
        }
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public Result update(AuthData authData) {
        try {
            sysAuthDataService.update(authData);
            return Result.ok("更新成功！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新失败！").put("title", "操作提示");
        }
    }

    @RequestMapping(value = "deleteBatch")
    @ResponseBody
    public Result deleteBatch(@RequestParam Map<String, Object> params) {
        try {
            sysAuthDataService.deleteBatch(params);
            return Result.ok("删除成功！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败!").put("title", "操作提示");
        }
    }

}
