package com.qh.water_management.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Author: qh
 * @Date: 2018/11/14 14:00
 * @Description: 生成验证码配置
 */
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "no");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.image.width", "125");
        properties.put("kaptcha.image.height", "45");
        properties.put("kaptcha.textproducer.font.size", "32");
        properties.put("kaptcha.session.key", "code");
        properties.put("kaptcha.textproducer.char.length", "5");
        properties.put("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
