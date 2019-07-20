package com.hlx.seller.service;

import com.hlx.api.ProductRpc;
import com.hlx.domain.ProductRpcReq;
import com.hlx.entity.Product;
import com.hlx.entity.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductRpcService {

    private static Logger logger= LoggerFactory.getLogger(ProductRpcService.class);

    @Autowired
    private ProductRpc productRpc;


   public List<Product> findAll(){
       ProductRpcReq rpcReq=new ProductRpcReq();
      List<String> status=new ArrayList<>();
      status.add(ProductStatus.IN_SELL.name());
       rpcReq.setStatusList(status);
       logger.info("rpc查询全部产品，请求:{}",rpcReq);
       List<Product> result=productRpc.query(rpcReq);
       logger.info("rpc查询全部产品，结果:{}",result);
       return result;
    }

    public Optional<Product> findOne(String id){
       logger.info("查询单个产品，请求:{}",id);
       Optional<Product> result=productRpc.findOne(id);
       logger.info("查询单个产品信息,结果:{}",result);
       return result;
    }

    @PostConstruct
    public void init(){
       findOne("0001");
    }
}
