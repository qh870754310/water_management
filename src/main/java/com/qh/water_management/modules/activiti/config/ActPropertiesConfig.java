package com.qh.water_management.modules.activiti.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: qh
 * @Date: 2019/1/8 14:12
 * @Description: 可以自定义一些配置类
 */
@Configuration
@PropertySource("classpath:properties\\activiti.properties")
public class ActPropertiesConfig {

    @Value("${modelId}")
    private String modelId;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    @Bean
    public ActPropertiesConfig getActPropertiesConfig(){
        return new ActPropertiesConfig();
    }
}
