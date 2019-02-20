package com.qh.water_management.component.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.PropertyResolver;
import org.springframework.stereotype.Service;

/**
 * @Author: qh
 * @Date: 2018/11/19 16:16
 * @Description:
 */
@Service
public class WebAppListener implements ApplicationListener<ContextRefreshedEvent> {
    //@Configuration
//public class WebAppListener implements EnvironmentAware {
    private static final Logger logger = LoggerFactory.getLogger(WebAppListener.class);

    private PropertyResolver propertyResolver;

    @Autowired
    ApplicationContext webApplicationContext;

    /**
     * 实现EnvironmentAware接口，初始化系统数据。
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        codeCache();
    }

    /**
     * 缓存全部数据字典
     */
    public void codeCache() {

    }
}
