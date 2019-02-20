package com.qh.water_management.config.security;

import com.alibaba.druid.support.http.WebStatFilter;
import com.qh.water_management.modules.common.xss.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;

/**
 * @Author: qh
 * @Date: 2018/11/15 15:30
 * @Description: 过滤器配置
 * springboot注入过滤器有多种方式，一种是最简单的@WebFilter注解，一种就是下边的这种，写一个FilterRegistrationBean，
 * 然后将自定义过滤器set进去，下边是通过DelegatingFilterProxy 代理的方式，注入容器中名字为shiroFilter 的过滤器，
 * 最后设置过滤器的规则
 *
 *
 */
@Configuration
public class FilterConfig {

    /**
     * 注册FilterRegistrationBean
     * DelegatingFilterProxy作用是自动到spring容器查找名字为shiroFilter（filter-name）的bean并
     * 把所有Filter的操作委托给它。然后将ShiroFilter配置到spring容器即可
     * @return
     */
    @Bean
    public FilterRegistrationBean shiroFilterRegistration() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        //配置Shiro的过虑器，如果与Spring集成，则必须要使用Shiro提供的过虑器代理
        //指定SpringBean代理Bean的名称，如果没有指定则与过虑器名称保持一致
        registrationBean.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //该值缺省为false，表示生命周期由SpringApplicationContext管理，设置为true则表示由ServletContainer管理
        registrationBean.addInitParameter("targetFilterLifecycle", "true");
        //配置是否启动过虑器的init/destory方法
        registrationBean.setEnabled(true);
        registrationBean.setOrder(Integer.MAX_VALUE - 2);
        //添加过滤规则.即所有的请求
        registrationBean.addUrlPatterns("/*");
        //添加需要,忽略的格式信息.
    //    registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        return registrationBean;
    }

    /**
     * XSS过滤器注册
     * @return
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        registrationBean.setFilter(new XssFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("xssFilter");
        registrationBean.setOrder(Integer.MAX_VALUE);
        return registrationBean;
    }

    /**
     * druid过滤器注册
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new WebStatFilter());
        //添加过滤规则.
        registrationBean.addUrlPatterns("/*");
        //添加需要忽略的格式信息.
        registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        registrationBean.setOrder(Integer.MAX_VALUE - 1);
        return registrationBean;
    }
}
