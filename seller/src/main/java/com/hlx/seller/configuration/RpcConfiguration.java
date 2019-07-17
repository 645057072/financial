package com.hlx.seller.configuration;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;
import com.hlx.api.ProductRpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 创建RPC客户端连接
 */
@Configuration
@ComponentScan(basePackageClasses = {ProductRpc.class})
public class RpcConfiguration {
    private static Logger logger= LoggerFactory.getLogger(RpcConfiguration.class);


    @Bean
    public AutoJsonRpcClientProxyCreator autoJsonRpcClientProxyCreator(@Value("${rpc.manager.url}") String url){

        AutoJsonRpcClientProxyCreator creator=new AutoJsonRpcClientProxyCreator();
        try {
            creator.setBaseUrl(new URL(url));
        } catch (MalformedURLException e) {
            logger.error("创建rpc客户端连接,请求:{}",e);
        }
        creator.setScanPackage(ProductRpc.class.getPackage().getName());
        return creator;
    }
}
