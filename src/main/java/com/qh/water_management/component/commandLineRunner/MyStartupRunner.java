package com.qh.water_management.component.commandLineRunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: qh
 * @Date: 2018/11/19 16:07
 * @Description: 服务启动执行
 */
@Component
public class MyStartupRunner implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        System.out.println("====================================服务启动执行==============================");

    }
}
