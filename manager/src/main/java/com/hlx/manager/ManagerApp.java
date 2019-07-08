package com.hlx.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * 管理启动类
 *
 *
 * */

@SpringBootApplication
@EntityScan(basePackages = "com.hlx.entity")
public class ManagerApp {
    public static void main(String[] args){
        SpringApplication.run(ManagerApp.class);
    }
}
