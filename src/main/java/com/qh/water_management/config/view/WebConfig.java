package com.qh.water_management.config.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * @Author: qh
 * @Date: 2018/11/22 16:33
 * @Description: 主要配置多视图实现的视图解析器相关bean实例
 *
 * 问题描述
 * SpringBoot如何同时访问html和jsp
 * SpringBoot访问html页面可以，访问jsp页面报错
 * SpringBoot如何同时整合thymeleaf html、vue html和jsp
 * java web spring mvc项目如何同时访问html和jsp
 * 其实关键点在于两个：
 * 1、配置order属性
 * 2、配置viewnames属性
 * 注意：
 * return new ModelAndView("jsps/index");//或者return "jsps/index" 对应 /WEB-INF/jsps/index.jsp
 * 同理：
 * return "thymeleaf/index";//或者return “thymeleaf/index” 对应 /WEB-INF/thymeleaf/index.html
 *
 * 注意：
 * 如果继承了WebMvcConfigurerSupport,则在yml中配置的相关内容会失效
 * WebMvcConfigurerAdapter是spring给出的一些默认配置，如静态资源默认最优先访问resources下的static里的文件（html）
 *
 *
 */
@Configuration
@ComponentScan("com.qh.water_management")
public class WebConfig {

    /**
     * 当没有声明ViewResolver时，spring会注册一个默认的ViewResolver，就是JstlView的实例， 该对象继承自InternalResoureView。
     * JstlView用来封装JSP或者同一Web应用中的其他资源，它将model对象作为request请求的属性值暴露出来, 并将该请求通过javax.servlet.RequestDispatcher转发到指定的URL.
     * Spring认为， 这个view的URL是可以用来指定同一web应用中特定资源的，是可以被RequestDispatcher转发的。
     * 也就是说，在页面渲染(render)之前，Spring会试图使用RequestDispatcher来继续转发该请求。
     *
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        resolver.setViewNames("jsp/*");
        resolver.setOrder(2);
        return resolver;
    }

    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setTemplateMode("LEGACYHTML5");
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("utf-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }


    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    /**
     * thymelea模板配置
     *
     * @return
     */
    @Bean
    public ThymeleafViewResolver viewResolverThymeLeaf() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("utf-8");
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setOrder(1);
        viewResolver.setViewNames(new String[]{"thymeleaf/*", "vue/*"});
        return viewResolver;
    }
}


/**
 * 配置静态资源的访问
 * spring boot默认是对静态资源做了映射的，默认配置的 /** 映射到 /static (或/public、/resources、/META-INF/resources)其中默认配置的 /webjars/** 映射到 classpath:/META-INF/resources/webjars/ 
 *
 *
 */


