package com.hlx.manager.rpc;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.hlx.api.ProductRpc;
import com.hlx.domain.ProductRpcReq;
import com.hlx.entity.Product;
import com.hlx.manager.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AutoJsonRpcServiceImpl
@Service
public class ProductRpcImpl implements ProductRpc {
    private static Logger logger= LoggerFactory.getLogger(ProductRpcImpl.class);
    @Autowired
    private ProductService productService;

    @Override
    public List<Product> query(ProductRpcReq req) {
        logger.info("查询多个产品,参数={}",req);
        Pageable pageable=new PageRequest(0,1000, Sort.Direction.DESC,"rewardRate");
        Page<Product> result=productService.query(req.getIdList(),req.getMinRewardRate(),req.getMaxRewardRate(),req.getStatusList(),pageable);
        logger.debug("查询多个产品，结果={}",result);
        return result.getContent();
    }

    @Override
    public Optional<Product> findOne(String id) {
        logger.info("查询单个产品,参数={}",id);
        Optional<Product> result=productService.findOne(id);
        logger.debug("查询产品详情,结果：{}",result);
        return result;
    }
}
