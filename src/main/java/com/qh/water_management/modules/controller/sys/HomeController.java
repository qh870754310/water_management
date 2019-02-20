package com.qh.water_management.modules.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: qh
 * @Date: 2018/10/23 14:47
 * @Description:
 */
@Controller
public class HomeController {

    @GetMapping(value = "/main")
    public String home() {
        return "thymeleaf/main";
    }
}
