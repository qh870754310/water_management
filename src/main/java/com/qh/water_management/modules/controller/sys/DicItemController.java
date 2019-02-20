package com.qh.water_management.modules.controller.sys;

import com.qh.water_management.modules.common.utils.BeanHelper;
import com.qh.water_management.modules.common.utils.Query;
import com.qh.water_management.modules.common.utils.Result;
import com.qh.water_management.modules.entity.sys.DictionaryDetail;
import com.qh.water_management.modules.service.sys.DicItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/10/29 12:55
 * @Description:
 */
@Controller
@RequestMapping(value = "/system/dicItem/")
public class DicItemController {

    @Autowired
    private DicItemService dicItemService;

    /**
     * 查询字典项
     * @param params
     * @return
     */
    @RequestMapping(value = "getPageSet")
    @ResponseBody
    public Map<String, Object> getPageSet(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        return dicItemService.getPageSet(query);
    }

    /**
     * 保存
     * @param dictionaryDetail
     * @return
     */
    @RequestMapping(value = "save")
    @ResponseBody
    public Result save(DictionaryDetail dictionaryDetail, String uuid) {
        try {
            dicItemService.save(dictionaryDetail, uuid);
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
            DictionaryDetail dictionaryDetail = (DictionaryDetail) BeanHelper.mapToObject_1(params, DictionaryDetail.class);
            dicItemService.update(dictionaryDetail);
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
            dicItemService.delete(params);
            return Result.ok("删除成功！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败！").put("title", "操作提示");
        }
    }

}
