package com.qh.water_management.config.mybatis;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 *
 * @Author: qh
 * @Date: 2018/11/13 16:57
 * @Description: Mybatis分页配置
 */
@Configuration
public class MybatisConfig {

    @Bean
    public PageHelper pageHelper() {
        System.out.print("MybatisConfig.pageHelper()");
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
