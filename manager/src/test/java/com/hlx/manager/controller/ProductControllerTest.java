package com.hlx.manager.controller;

import com.hlx.entity.Product;
import com.hlx.entity.enums.ProductStatus;
import com.hlx.util.RestUitl;
import org.apache.tomcat.jni.Local;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    private static RestTemplate rest=new RestTemplate();

    @Value("http//localhost:8081")
    private String baseUrl;

    private static List<Product> noramls=new ArrayList<>();
    @BeforeClass
    public static void init(){
        Product p1=new Product("T001","灵活宝1号", ProductStatus.AUDITING.name(),
                BigDecimal.valueOf(10),BigDecimal.valueOf(1),BigDecimal.valueOf(3.86));
        Product p2=new Product("T002","储蓄1号", ProductStatus.AUDITING.name(),
                BigDecimal.valueOf(10),BigDecimal.valueOf(1),BigDecimal.valueOf(3.86));
        Product p3=new Product("T003","期货宝1号", ProductStatus.AUDITING.name(),
                BigDecimal.valueOf(10),BigDecimal.valueOf(1),BigDecimal.valueOf(3.86));
            noramls.add(p1);
            noramls.add(p2);
            noramls.add(p3);
    }

    @Test
    public void create(){
        noramls.forEach(product ->{
            Product result=RestUitl.postJSON(rest,baseUrl+"products/add_product",product,Product.class);
            Assert.notNull(result.getCreateAt(),"插入失败");
        } );
        
    }

}
