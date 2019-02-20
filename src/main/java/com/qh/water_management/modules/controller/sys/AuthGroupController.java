package com.qh.water_management.modules.controller.sys;

import com.qh.water_management.modules.common.utils.Result;
import com.qh.water_management.modules.entity.sys.AuthGroup;
import com.qh.water_management.modules.service.sys.AuthGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/12/12 13:57
 * @Description: 角色权限
 */
@Controller
@RequestMapping(value = "/system/authGroup/")
public class AuthGroupController {

    @Autowired
    private AuthGroupService authGroupService;

    @RequestMapping(value = "index")
    public String index() {
        return "vue/sys/authGroup/index";
    }

    @RequestMapping(value = "edit")
    public String edit() {
        return "vue/sys/authGroup/edit";
    }

    @RequestMapping(value = "getListByLevelId")
    @ResponseBody
    public Object getListByLevelId(Integer levelId) {
        return authGroupService.getListByLevelId(levelId);
    }

    @RequestMapping(value = "getListByParentId")
    @ResponseBody
    public Object getListByParentId(String parentId) {
        return authGroupService.getListByParentId(parentId);
    }

    @RequestMapping(value = "getListByCodeSetIdAndLevelId")
    @ResponseBody
    public Object getListByCodeSetIdAndLevelId(String codeSetId, Integer levelId) {
        Map<String, Object> params = new HashMap<>();
        params.put("codeSetId", codeSetId);
        params.put("levelId", levelId);
        return authGroupService.getListByCodeSetIdAndLevelId(params);
    }

    @RequestMapping(value = "getDetailByUuid")
    @ResponseBody
    public Map<String, Object> getDetailByUuid(String uuid) {
        return authGroupService.getDetailByUuid(uuid);
    }

    @RequestMapping(value = "save")
    @ResponseBody
    public Result save(AuthGroup authGroup) {
        try {
            authGroupService.save(authGroup);
            return Result.ok("保存成功！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("保存失败！").put("title", "操作提示");
        }
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public Integer update(AuthGroup authGroup) {
        Integer flag = -1;
        try {
            flag = authGroupService.update(authGroup);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public Result delete(@RequestParam Map<String, Object> params) {
        try {
            authGroupService.delete(params);
            return Result.ok("删除成功！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败！").put("title", "操作提示");
        }
    }
}
