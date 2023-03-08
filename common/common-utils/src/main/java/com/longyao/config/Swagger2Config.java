package com.longyao.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("前台Api")
                .apiInfo(webApiInfo())
                .select()
                //只显示front路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/labelImg/front/.*")))
                .build();
    }

    @Bean
    public Docket adminApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("后台Api")
                .apiInfo(adminApiInfo())
                .select()
                //只显示afte路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/labelImg/after/.*")))
                .build();
    }
    @Bean
    public Docket userApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户Api")
                .apiInfo(adminApiInfo())
                .select()
                //只显示afte路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/labelImg/user/.*")))
                .build();
    }
    @Bean
    public Docket roleApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("角色Api")
                .apiInfo(adminApiInfo())
                .select()
                //只显示afte路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/labelImg/role/.*")))
                .build();
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("前台-API文档")
                .description("本文档描述了网站微服务接口定义")
                .version("1.0")
                .contact(new Contact("long", "http://lzxzl.cn", "2252009936@qq.com"))
                .build();
    }

    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("后台管理系统-API文档")
                .description("本文档描述了后台管理系统微服务接口定义")
                .version("1.0")
                .contact(new Contact("long", "http://lzxzl.cn", "2252009936@qq.com"))
                .build();
    }

    private ApiInfo userApiInfo(){
        return new ApiInfoBuilder()
                .title("用户-API文档")
                .description("本文档描述了网站微服务接口定义")
                .version("1.0")
                .contact(new Contact("long", "http://lzxzl.cn", "2252009936@qq.com"))
                .build();
    }
    private ApiInfo roleApiInfo(){
        return new ApiInfoBuilder()
                .title("角色-API文档")
                .description("本文档描述了网站微服务接口定义")
                .version("1.0")
                .contact(new Contact("long", "http://lzxzl.cn", "2252009936@qq.com"))
                .build();
    }
}

