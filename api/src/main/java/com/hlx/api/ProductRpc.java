package com.hlx.api;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.hlx.domain.ProductRpcReq;
import com.hlx.entity.Product;
import org.springframework.data.domain.Page;

import java.util.Optional;

@JsonRpcService("/products")
public interface ProductRpc {
    /**
     *
     * 查询多个产品
     * @param productRpcReq
     * @return
     */
    Page<Product> query(ProductRpcReq productRpcReq);


    Optional<Product> findOne(String id);


}
