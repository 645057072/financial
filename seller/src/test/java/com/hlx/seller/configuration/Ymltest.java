package com.hlx.seller.configuration;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages = "com.hlx.seller")
public class Ymltest {
    @Value("${rpc.manager.url}")
    private String url;

    @Test
    public void test( String url){
        System.out.println(url);
    }
}
