package com.qh.water_management.config.shiro;

import com.qh.water_management.component.shiro.AuthRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/11/15 15:46
 * @Description: Shiro的配置类
 */
@Configuration
public class ShiroConfig {

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //Shiro的核心安全接口,这个属性是必须的(// 必须设置 SecurityManager)
        //这句的配置，即引入设置shiro的控制中心，即securityManager ，安全管理器；即所有与安全有关的操作都会与securityManager交互；
        // 且它管理着所有Subject；可以看出它是Shiro的核心，它负责与后边介绍的其他组件进行交互，如果学习过SpringMVC，
        // 你可以把它看成DispatcherServlet前端控制器；关于subject ，你就理解为是每一个访问系统的用户对象即可，所有的访问用户的情况
        // 都是一种subject 的体现，它们又统一被securityManager 管理
        shiroFilter.setSecurityManager(securityManager);
        //配置登录的url和登录成功的url
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.html"页面
        // 登录路径,shiro配置的loginUrl在session过期时跳转至登录页面url的过程是针对普通数据访问方式的情况下，在异步数据访问方式下需要另外处理。
        shiroFilter.setLoginUrl("/");
        shiroFilter.setSuccessUrl("/index");
        //未授权界面;用户访问无权限的链接时跳转此页面
        shiroFilter.setUnauthorizedUrl("/403.html");
        //配置访问权限
        //shiro不拦截资源,过滤链定义
        //shiro配置过滤规则少量的话可以用hashMap,数量多了要用LinkedHashMap,保证有序
        //shiro自己的过滤器,anon，表示不拦截的路径，authc,表示拦截的路径
        //匹配时，首先匹配anon的，然后最后匹配authc
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/", "anon");
        filterMap.put("/druid/**", "anon");
        filterMap.put("/static/**", "anon");
        filterMap.put("/error/**", "anon");
        filterMap.put("/json/**", "anon");
        filterMap.put("/resource/**", "anon");
        filterMap.put("/resource/plugins/**", "anon");
        filterMap.put("/resource/public/css/*.css", "anon");
        filterMap.put("/resource/public/js/*.js", "anon");
        filterMap.put("/thymeleaf/*.html", "anon");
        filterMap.put("/vue/**/**/*.html", "anon");
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/login/captcha", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/**", "authc");
        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

    /**
     * 配置核心安全事务管理器
     * @param authRealm
     * @return
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        System.out.print("--------------shiro已经加载----------------");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //该句引入了我们的第三个配置文件，即MyRealm 文件，改文件又引出了我们的一个概念，
        // Realm：域Shiro从从Realm获取安全数据（如用户、角色、权限），就是说SecurityManager要验证用户身份，
        // 那么它需要从Realm获取相应的用户进行比较以确定用户身份是否合法；也需要从Realm得到用户相应的角色/权限进行验证
        // 用户是否能进行操作；可以把Realm看成DataSource，即安全数据源。
        //设置realm.
        securityManager.setRealm(authRealm);
        // 自定义session管理 使用redis
        //可选项 默认使用ServletContainerSessionManager，直接使用容器的HttpSession，可以通过配置sessionManager，使用DefaultWebSessionManager来替代
        /*将session托管给redis进行管理，便于搭建集群系统*/
        //securityManager配置sessionManager之后，springboot中配置的session有效时间无效（sessionManager管理器覆盖了springboot中session有效时间的配置）。
      //  securityManager.setSessionManager();
        return securityManager;
    }

    /**
     * 配置自定义的权限登录器
     * @return
     */
    @Bean(name = "authRealm")
    public AuthRealm authRealm() {
        AuthRealm authRealm=new AuthRealm();
        return authRealm;
    }

    /**
     * 必须配置这个类
     * shiro有其自己管理生命周期的类，各个bean需要Dependon这个类进行加载
     * 保证实现了Shiro内部lifecycle函数的bean执行
     * @return
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * AOP式方法级权限检查
     * 以下是两个启用注解的权限控制
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     *
     * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
