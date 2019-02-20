package com.qh.water_management.modules.controller.sys;

import com.qh.water_management.modules.common.utils.BeanHelper;
import com.qh.water_management.modules.common.utils.Query;
import com.qh.water_management.modules.common.utils.Result;
import com.qh.water_management.modules.entity.sys.CodeItem;
import com.qh.water_management.modules.service.sys.CodeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/12/12 13:53
 * @Description: 树形菜单
 */
@Controller
@RequestMapping(value = "/system/codeItem/")
public class CodeItemController {

    @Autowired
    private CodeItemService codeItemService;

    @RequestMapping(value = "index")
    public String index(String codeSetId, String levelId) {
        return "vue/sys/codeItem/index";
    }

    @RequestMapping(value = "editRoot")
    public String editRoot() {
        return "vue/sys/codeItem/editRoot";
    }

    @RequestMapping(value = "edit")
    public String edit() {
        return "vue/sys/codeItem/edit";
    }

    @RequestMapping(value = "getListByLevelId")
    @ResponseBody
    public Object getListByLevelId(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        return codeItemService.getListByLevelId(query);
    }

    @RequestMapping(value = "getListByParentId")
    @ResponseBody
    public Object getListByParentId(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        return codeItemService.getListByParentId(query);
    }

    @RequestMapping(value = "getListByCodeSetIdAndLevelId")
    @ResponseBody
    public Object getListByCodeSetIdAndLevelId(String codeSetId, Integer levelId) {
        Map<String, Object> params = new HashMap<>();
        params.put("codeSetId", codeSetId);
        params.put("levelId", levelId);
        return codeItemService.getListByCodeSetIdAndLevelId(params);
    }

    @RequestMapping(value = "getFatherIds")
    @ResponseBody
    public String getFatherIds(String uuid) {
        return codeItemService.getFatherIds(uuid);
    }

    @RequestMapping(value = "getDetailByUuid")
    @ResponseBody
    public Map<String, Object> getDetailByUuid(String uuid) {
        return codeItemService.getDetailByUuid(uuid);
    }

    @RequestMapping(value = "getZoneFillbackData")
    @ResponseBody
    public Map<String, Object> getZoneFillbackData(String id) {
        String zoneFillbackData = codeItemService.getZoneFillbackData(id);
        String[] split = zoneFillbackData.split(",");
        Map<String, Object> map = new HashMap<>();
        map.put("country", split[0]);
        map.put("province", split[1]);
        map.put("district", split[2]);
        map.put("city", split[3]);
        return map;
    }

    @RequestMapping(value = "saveRoot")
    @ResponseBody
    public Result saveRoot(CodeItem codeItem) {
        try {
            codeItemService.saveRoot(codeItem);
            return Result.ok("保存成功！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("保存失败！").put("title", "操作提示");
        }
    }

    @RequestMapping(value = "save")
    @ResponseBody
    public Result save(CodeItem codeItem) {
        try {
            codeItemService.save(codeItem);
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
            CodeItem codeItem = (CodeItem) BeanHelper.mapToObject_1(params, CodeItem.class);
            codeItemService.update(codeItem);
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
            codeItemService.delete(params);
            return Result.ok("删除成功！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败!").put("title", "操作提示");
        }
    }
}
