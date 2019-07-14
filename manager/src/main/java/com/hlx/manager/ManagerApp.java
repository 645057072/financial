package com.hlx.manager;

import com.hlx.swagger.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

/**
 * 管理启动类
 *
 *
 * */

@SpringBootApplication
@EntityScan(basePackages = "com.hlx.entity")
@Import(SwaggerConfiguration.class)//引入swagger配置类
public class ManagerApp {
    public static void main(String[] args){
        SpringApplication.run(ManagerApp.class);
    }
}
