package com.qh.water_management.modules.controller.sys;

import com.qh.water_management.modules.common.utils.BeanHelper;
import com.qh.water_management.modules.common.utils.Result;
import com.qh.water_management.modules.entity.sys.Organization;
import com.qh.water_management.modules.service.sys.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/12/10 17:13
 * @Description: 组织机构管理
 */
@Controller
@RequestMapping(value = "/mdata/organization/")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value = "index")
    public String index() {
        return "vue/sys/organization/index";
    }

    @RequestMapping(value = "getListByLevelId")
    @ResponseBody
    public Object getListByLevelId(Integer levelId) {
        return organizationService.getListByLevelId(levelId);
    }

    @RequestMapping(value = "getListByParentId")
    @ResponseBody
    public Object getListByParentId(String parentId) {
        return organizationService.getListByParentId(parentId);
    }

    @RequestMapping(value = "getFatherIds")
    @ResponseBody
    public Object getFatherIds(String id) {
        if ("undefined".equals(id)) {
            return Result.error("未知错误");
        } else {
            return organizationService.getFatherIds(id);
        }
    }

    @RequestMapping(value = "editRoot")
    public String editRoot(boolean editModel) {
        return "vue/sys/organization/editRoot";
    }

    @RequestMapping(value = "edit")
    public String edit(boolean editModel) {
        return "vue/sys/organization/edit";
    }

    @RequestMapping(value = "saveRoot")
    @ResponseBody
    public Result saveRoot(Organization organization) {
        try {
            organizationService.saveRoot(organization);
            return Result.ok("保存成功！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("保存失败！").put("title", "操作提示");
        }
    }

    @RequestMapping(value = "save")
    @ResponseBody
    public Result save(Organization organization) {
        try {
            organizationService.save(organization);
            return Result.ok("保存成功！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("保存失败！").put("title", "操作提示");
        }
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public Result update(@RequestParam Map<String, Object> params) {
        try {
            Organization organization = (Organization) BeanHelper.mapToObject_1(params, Organization.class);
            organizationService.update(organization);
            return Result.ok("更新成功！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新失败！").put("title", "操作提示");
        }
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public Result delete(@RequestParam Map<String, Object> params) {
        try {
            organizationService.delete(params);
            return Result.ok("删除成功！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败！").put("title", "操作提示");
        }
    }


    @RequestMapping(value = "getDetailByUuid")
    @ResponseBody
    public Map<String, Object> getDetailByUuid(String uuid) {
        return organizationService.getDetailByUuid(uuid);
    }
}
