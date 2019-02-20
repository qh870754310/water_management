package com.qh.water_management.modules.controller.activiti;

import com.qh.water_management.modules.common.utils.Query;
import com.qh.water_management.modules.common.utils.Result;
import com.qh.water_management.modules.entity.activiti.ExtendActModelEntity;
import com.qh.water_management.modules.service.activiti.ExtendActModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author: qh
 * @Date: 2019/1/4 16:56
 * @Description: 流程模型相关操作
 */

@Controller
@RequestMapping(value = "/act/model/")
public class ExtendActModelController {

    private Logger logger = LoggerFactory.getLogger(ExtendActModelController.class);

    @Autowired
    ExtendActModelService extendActModelService;

    @GetMapping(value = "list")
    public String index() {
        return "vue/activiti/model/index";
    }

    @GetMapping(value = "edit")
    public String edit() {
        return "vue/activiti/model/edit";
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
        return extendActModelService.getPageSetData(query);
    }

    @RequestMapping(value = "getDetailByUuid")
    @ResponseBody
    public Object getDetailByUuid(String uuid) {
        return extendActModelService.getDetailByUuid(uuid);
    }


    @RequestMapping(value = "save")
    @ResponseBody
    public Result save(ExtendActModelEntity actModelEntity) {
        try {
            String modelId = extendActModelService.save(actModelEntity);
            return Result.ok("保存成功").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("保存失败").put("title", "操作提示");
        }
    }


    @RequestMapping(value = "update")
    @ResponseBody
    public Result update(ExtendActModelEntity actModelEntity) {
        try {
            int count = extendActModelService.update(actModelEntity);
            if (count > 0 ) {
                return Result.ok("更新成功").put("title", "操作提示");
            } else {
                return Result.error("更新失败").put("title", "操作提示");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新失败").put("title", "操作提示");
        }
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public Result delete(String uuid) {
        try {
            String[] ids = uuid.split(",");
            extendActModelService.delete(ids);
            return Result.ok("删除成功").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败").put("title", "操作提示");
        }
    }
}
