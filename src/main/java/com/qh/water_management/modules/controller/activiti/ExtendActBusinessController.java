package com.qh.water_management.modules.controller.activiti;

import com.qh.water_management.modules.common.utils.Query;
import com.qh.water_management.modules.common.utils.Result;
import com.qh.water_management.modules.entity.activiti.ExtendActBusinessEntity;
import com.qh.water_management.modules.service.activiti.ExtendActBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2019/1/9 11:27
 * @Description: 业务树
 */
@Controller
@RequestMapping("act/bus/")
public class ExtendActBusinessController {

    @Autowired
    ExtendActBusinessService businessService;

    @GetMapping(value = "busTree")
    public String index() {
        return "vue/activiti/bus/busTree";
    }

    @RequestMapping(value = "editRoot")
    public String editRoot() {
        return "vue/activiti/bus/editRoot";
    }

    @GetMapping(value = "edit")
    public String edit() {
        return "vue/activiti/bus/edit";
    }
    @RequestMapping(value = "getListByLevelId")
    @ResponseBody
    public Object getListByLevelId(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        return businessService.getListByLevelId(query);
    }

    @RequestMapping(value = "getListByActKeyAndLevelId")
    @ResponseBody
    public Object getListByActKeyAndLevelId(String actKey, Integer levelId) {
        Map<String, Object> params = new HashMap<>();
        params.put("actKey", actKey);
        params.put("levelId", levelId);
        return businessService.getListByActKeyAndLevelId(params);
    }

    @RequestMapping(value = "getListByParentId")
    @ResponseBody
    public Object getListByParentId(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        return businessService.getListByParentId(query);
    }

    @RequestMapping(value = "getFatherIds")
    @ResponseBody
    public String getFatherIds(String uuid) {
        return businessService.getFatherIds(uuid);
    }

    @RequestMapping(value = "getDetailByUuid")
    @ResponseBody
    public Map<String, Object> getDetailByUuid(String uuid) {
        return businessService.getDetailByUuid(uuid);
    }

    @RequestMapping(value = "saveRoot")
    @ResponseBody
    public Result saveRoot(ExtendActBusinessEntity actBusinessEntity) {
        try {
            businessService.saveRoot(actBusinessEntity);
            return Result.ok("保存成功").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("保存失败").put("title", "操作提示");
        }
    }

    @RequestMapping(value = "save")
    @ResponseBody
    public Result save(ExtendActBusinessEntity actBusinessEntity) {
        try {
            businessService.save(actBusinessEntity);
            return Result.ok("保存成功").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("保存失败").put("title", "操作提示");
        }
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public Result update(ExtendActBusinessEntity actBusinessEntity) {
        try {
            businessService.update(actBusinessEntity);
            return Result.ok("更新成功").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新失败").put("title", "操作提示");
        }
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public Result delete(@RequestParam Map<String, Object> params) {
        try {
            businessService.delete(params);
            return Result.ok("删除成功").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败").put("title", "操作提示");
        }
    }

}
