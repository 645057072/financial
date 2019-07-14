package com.hlx.swagger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.awt.geom.AreaOp;
import sun.security.krb5.internal.crypto.DesMacCksumType;

import java.util.HashSet;

@Configuration
@ComponentScan(basePackages = "com.hlx.swagger")
@EnableSwagger2
public class SwaggerConfiguration {
    @Autowired
    private SwaggerInfo swaggerInfo;
    @Bean
    public Docket controllerApi(){
        Docket docket= new Docket(DocumentationType.SWAGGER_2)
                .groupName(swaggerInfo.getGroupName())
                .apiInfo(apiInfo());
        ApiSelectorBuilder builder=docket.select();
        if (!StringUtils.isEmpty(swaggerInfo.getBasePackage())){
            builder.apis(RequestHandlerSelectors.basePackage(swaggerInfo.getBasePackage()));
        }
        if (!StringUtils.isEmpty(swaggerInfo.getAntPath())){
            builder.paths(PathSelectors.ant(swaggerInfo.getAntPath()));
        }
        return builder.build();


    }


  private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(swaggerInfo.getTitle())
                .description(swaggerInfo.getDescription())
                .termsOfServiceUrl("http://springfox.io")
                .contact("leovi")
                .license(swaggerInfo.getLicense())
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .version("2.0")
                .build();
    }
}
