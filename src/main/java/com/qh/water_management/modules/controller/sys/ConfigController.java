package com.qh.water_management.modules.controller.sys;

import com.qh.water_management.modules.common.utils.BeanHelper;
import com.qh.water_management.modules.common.utils.Query;
import com.qh.water_management.modules.common.utils.Result;
import com.qh.water_management.modules.entity.sys.BasicConfig;
import com.qh.water_management.modules.service.sys.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/10/29 16:22
 * @Description:
 */
@Controller
@RequestMapping(value = "/system/config/")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @GetMapping(value = "index")
    public String index() {
        return "vue/sys/config/index";
    }

    /**
     * 1、用Map接收前端提交的Form Data或Query String，如果有相同的参数名，只接收第一个
     * 2、用MultiValueMap接收前端提交的Form Data或Query String，可以接收相同的参数名的值到同一个list中(@RequestParam MultiValueMap<String, Object> params)
     * 3、用Map接收前端提交的json格式的Request Payload，如果有相同的参数名，只接收最后一个
     * @param params
     * @return
     */
    @RequestMapping(value = "getPageSetData")
    @ResponseBody
    public Map<String, Object> getPageSetData(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        return configService.getPageSetData(query);
    }

    /**
     * 添加
     * @param basicConfig
     */
    @RequestMapping(value = "save")
    @ResponseBody
    public Result save(BasicConfig basicConfig) {
        try {
            configService.save(basicConfig);
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
            BasicConfig basicConfig = (BasicConfig) BeanHelper.mapToObject_1(params, BasicConfig.class);
            configService.update(basicConfig);
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
            configService.delete(params);
            return Result.ok("删除成功！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败！").put("title", "操作提示");
        }
    }
}
