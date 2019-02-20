package com.qh.water_management.modules.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.qh.water_management.modules.common.utils.ExcelUtil;
import com.qh.water_management.modules.common.utils.Query;
import com.qh.water_management.modules.common.utils.Result;
import com.qh.water_management.modules.entity.sys.User;
import com.qh.water_management.modules.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @Author: qh
 * @Date: 2018/11/16 11:17
 * @Description:
 */
@Controller
@RequestMapping(value = "/mdata/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "index")
    public String index() {
        return "vue/sys/user/index";
    }

    @RequestMapping(value = "edit")
    public String edit() {
        return "vue/sys/user/edit";
    }

    @RequestMapping(value = "getListByKeywords")
    @ResponseBody
    public Object getListByKeywords() {
        return userService.getListByKeywords();
    }

    @RequestMapping(value = "getPageSet")
    @ResponseBody
    public Map<String, Object> getPageSet(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        return userService.getPageSet(query);
    }

    @RequestMapping(value = "getDetailByUuid")
    @ResponseBody
    public Object getDetailByUuid(String uuid) {
        return userService.getDetailByUuid(uuid);
    }

    @RequestMapping(value = "save")
    @ResponseBody
    public Result save(User user) {
        try {
            userService.save(user);
            return Result.ok("保存成功！").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("保存失败！").put("title", "操作提示");
        }
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public Integer update(User user) {
        return userService.update(user);
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public Result delete(User user) {
        try {
            userService.delete(user);
            return Result.ok("删除成功").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败").put("title", "操作提示");
        }
    }

    @RequestMapping(value = "updatePassword")
    @ResponseBody
    public Result updatePassword(User user) {
        try {
            userService.updatePassword(user);
            return Result.ok("密码重置成功").put("title", "操作提示");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("密码重置失败").put("title", "操作提示");
        }
    }

    @RequestMapping(value = "exportExcel")
    public void exportExcel(HttpServletResponse response, String excelTitle, String colName, String fieldName) throws Exception {
        List<String[]> columNames = new ArrayList<String[]>();
        columNames.add(colName.split(","));
        List<String[]>  fieldNames = new ArrayList<String[]>();
        fieldNames.add(fieldName.split(","));
        LinkedHashMap<String, List<?>> map = new LinkedHashMap<String, List<?>>();
        map.put(excelTitle, userService.getExportData());
        ExcelUtil.ExcelExportData setInfo = new ExcelUtil.ExcelExportData();
        setInfo.setDataMap(map);
        setInfo.setFieldNames(fieldNames);
        setInfo.setTitles(new String[]{"镇江市区燃气燃烧器具安装维修企业资质名单"});
        setInfo.setColumnNames(columNames);
        // 将需要导出的数据输出到文件
        String fileName = excelTitle;
        OutputStream out = response.getOutputStream();
        response.reset(); //清空response
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + ".xls");
        response.setContentType("application/x-download");
        ByteArrayOutputStream outputStream = ExcelUtil.export2Stream(setInfo);
        ExcelUtil.exportExcel(fileName, setInfo, out);
        out.close();
    }
}
