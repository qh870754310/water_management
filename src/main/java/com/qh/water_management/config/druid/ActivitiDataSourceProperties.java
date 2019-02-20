package com.qh.water_management.config.druid;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: qh
 * @Date: 2019/1/4 14:12
 * @Description:
 */
@Getter
@Setter
@Component
public class ActivitiDataSourceProperties  {

    @Value("${spring.datasource.druid.url}")
    private String url;

    @Value("${spring.datasource.druid.username}")
    private String username;

    @Value("${spring.datasource.druid.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

}
