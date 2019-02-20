package com.qh.water_management.modules.controller.sys;

import com.qh.water_management.modules.common.utils.BeanHelper;
import com.qh.water_management.modules.common.utils.Query;
import com.qh.water_management.modules.common.utils.Result;
import com.qh.water_management.modules.entity.sys.DictionaryCate;
import com.qh.water_management.modules.service.sys.DictSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/10/26 14:41
 * @Description:
 */
@Controller
@RequestMapping(value = "/system/dicSet/")
public class DictSetController {

    @Autowired
    private DictSetService dictSetService;

    @GetMapping(value = "index")
    public String index() {
        return "vue/sys/dicSet/index";
    }


    /**
     * 获取数据字典
     * @param code
     * @return
     */
    @PostMapping(value = "getDicItemByCode")
    @ResponseBody
    public Object getDicItemByCode(String code) {
        List<Map> list = dictSetService.getDicItemByCode(code);
        return list;
    }

    /**
     * 查询字典集
     * @param params
     * @return
     */
    @RequestMapping(value = "getPageSet")
    @ResponseBody
    public Map<String, Object> getPageSet(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        return dictSetService.getPageSet(query);
    }

    /**
     * 保存
     * @param dictionaryCate
     * @return
     */
    @RequestMapping(value = "save")
    @ResponseBody
    public Result save(DictionaryCate dictionaryCate) {
        try {
            dictSetService.save(dictionaryCate);
            return Result.ok().put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().put("title", "操作提示");
        }
    }

    /**
     * 编辑
     * @param params
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Result update(@RequestParam Map<String, Object> params) {
        try {
            DictionaryCate dictionaryCate = (DictionaryCate) BeanHelper.mapToObject_1(params, DictionaryCate.class);
            dictSetService.update(dictionaryCate);
            return Result.ok().put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().put("title", "操作提示");
        }
    }

    /**
     * 删除
     * @param params
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Result delete(@RequestParam Map<String, Object> params) {
        try {
            dictSetService.delete(params);
            return Result.ok("删除成功！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败！").put("title", "操作提示");
        }
    }
}
