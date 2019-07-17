package com.hlx.api;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.hlx.domain.ProductRpcReq;
import com.hlx.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * 查询产品RPC
 */
@JsonRpcService("rpc/products")
public interface ProductRpc {
    /**
     *
     * 查询多个产品
     * @param productRpcReq
     * @return
     */
    List<Product> query(ProductRpcReq productRpcReq);

    /**
     * 查询单个产品
     * @param id
     * @return
     */

    Optional<Product> findOne(String id);


}
