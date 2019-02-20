package com.qh.water_management.config.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author: qh
 * @Date: 2018/11/14 14:08
 * @Description: 跨域配置，在springboot中通过cors协议解决跨域问题
 * 基于Filter的CORS配置：
 * 处理ajax请求跨源资源的问题，因为需要所有 Web API 都需要支持跨源资源共享（CORS），所以需要进行全局设置。
 * Spring Boot 可以全局配置 CORS。这样就不用每个Controller都进行配置了，
 * 解决前后端分离调用时跨域问题.注意安全风险,更细粒度的控制,可在方法上 @CrossOrigin(origins = "url")
 * CORS配置类必须继承WebMvcConfigurerAdapter @Override addCorsMappings方法
 * addMapping：可以被跨域的路径，”/**”表示无限制。
 * allowedMethods：允许跨域访问资源服务器的请求方式，如：POST、GET、PUT、DELETE等，“*”表示无限制。
 * allowedOrigins：”*”允许所有的请求域名访问跨域资源，当设置具体URL时只有被设置的url可以跨域访问。
 * 例如：allowedOrigins(“https://www.baidu.com”),则只有https://www.baidu.com能访问跨域资源。
 *
 */
@Configuration
public class SecurityCorsConfiguration {

    /**
     * 注册拦截器
     * @return
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置你要允许的网站域名，如果全允许则设为 *
//      config.addAllowedOrigin("http://183.195.133.44:80");
//      config.addAllowedOrigin("http://localhost:8080");
        config.addAllowedOrigin(CorsConfiguration.ALL);
        // 如果要限制 HEADER 或 METHOD 请自行更改
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.addAllowedMethod(CorsConfiguration.ALL);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        // 这个顺序很重要哦，为避免麻烦请设置在最前
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}

/**
 * 1、对于前后端分离的项目来说，如果前端项目与后端项目部署在两个不同的域下，那么势必会引起跨域问题的出现。
 * 针对跨域问题，我们可能第一个想到的解决方案就是jsonp，并且以前处理跨域问题我基本也是这么处理。
 * 但是jsonp方式也同样有不足，不管是对于前端还是后端来说，写法与我们平常的ajax写法不同，同样后端也需要作出相应的更改。并且，jsonp方式只能通过get请求方式来传递参数，当然也还有其它的不足之处;
 * 2、在springboot中通过cors协议解决跨域问题
 * H5中的新特性：Cross-Origin Resource Sharing（跨域资源共享）。通过它，我们的开发者（主要指后端开发者）可以决定资源是否能被跨域访问。
 * cors是一个w3c标准，它允许浏览器（目前ie8以下还不能被支持）像我们不同源的服务器发出xmlHttpRequest请求，我们可以继续使用ajax进行请求访问。
 *
 *
 */
