package com.hlx.manager.configurations;

import com.google.common.collect.Lists;
import com.hlx.manager.controller.ProductController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket documentation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("controller")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(ProductController.class.getPackage().getName()))//定位扫描包路径
//                .paths(PathSelectors.ant("/products/*"))//根据路径选择方法
                .build();


    }

    @Bean
    ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("服务API")
                .description("管理端服务接口")
                .termsOfServiceUrl("http://springfox.io")
                .contact("leovi")
                .license("Apache License Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .version("2.0")
                .build();
    }
}
