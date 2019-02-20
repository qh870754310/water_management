package com.qh.water_management.modules.controller.generator;

import com.alibaba.fastjson.JSON;
import com.qh.water_management.modules.common.utils.PageUtils;
import com.qh.water_management.modules.common.utils.Query;
import com.qh.water_management.modules.common.xss.XssHttpServletRequestWrapper;
import com.qh.water_management.modules.service.generator.SysGeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2019/1/7 10:08
 * @Description: 代码生成器
 */
@Controller
@RequestMapping("/system/generator/")
public class SysGeneratorController {

    @Autowired
    private SysGeneratorService sysGeneratorService;

    @GetMapping(value = "index")
    public String index() {
        return "vue/generator/index";
    }

    /**
     * 列表
     * @param params
     * @return
     */
    @RequestMapping(value = "getPageSet")
    @ResponseBody
    public Map<String, Object> getPageSet(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<Map<String, Object>> list = sysGeneratorService.getPageSet(query);
        int total = sysGeneratorService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getRows(), query.getPage());
        Map<String, Object> map = new HashMap<>();
        map.put("rows", list);
        map.put("total", total);
        map.put("pages", pageUtil.getTotalPage());
        return map;
    }

    /**
     * 生成代码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("code")
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] tableNames = new String[]{};
        //获取表名，不进行xss过滤
        HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);
        String tables = orgRequest.getParameter("tables");
        tableNames = JSON.parseArray(tables).toArray(tableNames);
        byte[] data = sysGeneratorService.generatorCode(tableNames, com.qh.modules.common.common.Constant.genType.webDown.getValue());
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"sources.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }
}
