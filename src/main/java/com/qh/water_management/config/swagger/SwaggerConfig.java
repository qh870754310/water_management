package com.qh.water_management.config.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: qh
 * @Date: 2018/11/14 14:19
 * @Description: 为了方便进行测试后台的restful形式的接口，实现动态的更新，当我们在后台的接口修改了后，swagger可以实现自动的更新，而不需要认为的维护这个接口进行测试。
 * Swagger 是一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务。
 * 总体目标是使客户端和文件系统作为服务器以同样的速度来更新。文件的方法，参数和模型紧密集成到服务器端的代码，
 * 允许API来始终保持同步。Swagger 让部署管理和使用功能强大的API从未如此简单
 * Swagger会默认把所有Controller中的RequestMapping方法都生成API出来，实际上我们一般只需要标准接口的（像返回页面的那种Controller方法我们并不需要），
 * 所有你可以按下面的方法来设定要生成API的方法的要求
 *
 * @Api：修饰整个类，描述Controller的作用
 * @ApiOperation：描述一个类的一个方法，或者说一个接口
 * @ApiParam：单个参数描述
 * @ApiModel：用对象来接收参数
 * @ApiProperty：用对象接收参数时，描述对象的一个字段
 *
 */
@Configuration
// @EnableWebMvc // 启用Mvc //非springboot框架需要引入注解@EnableWebMvc
@EnableSwagger2  // 启用Swagger2，毕竟SpringFox的核心依旧是Swagger
@ConditionalOnProperty(prefix = "water_management", name = "swagger-open", havingValue = "true")
public class SwaggerConfig implements WebMvcConfigurer {


    /**
     * SpringBoot默认已经将classpath:/META-INF/resources/和classpath:/META-INF/resources/webjars/映射
     * 所以该方法不需要重写，如果在SpringMVC中，可能需要重写定义（我没有尝试）
     * 重写该方法需要 extends WebMvcConfigurerAdapter
     *
     */
    /**
     * 这个地方要重新注入一下资源文件，不然不会注入资源的，也没有注入requestHandlerMappping,相当于xml配置的
     *  swagger资源配置
     *  <mvc:resources location="classpath:/META-INF/resources/" mapping="swagger-ui.html"/>
     *  <mvc:resources location="classpath:/META-INF/resources/webjars/" mapping="/webjars/**"/>
     *  不知道为什么，这也是spring boot的一个缺点（菜鸟觉得的）
     * @param registry
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars*")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     * @return
     */
    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        tokenPar.name("x-access-token").description("口令").modelRef(new ModelRef("string")).parameterType("header").required(false).build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 选择那些路径和api会生成document
                .select()
    //         .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))    //这里采用包含注解的方式来确定要显示的接口
                //为当前包路径
                // 对该包下的api进行监控
                .apis(RequestHandlerSelectors.basePackage("com.qh.water_management.modules"))
                // 对该包下的所有路径进行监控
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring Boot 测试使用 Swagger2 构建RESTful API")
                //创建人
                .contact(new Contact("qh", "", ""))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }
}
