package com.hlx.manager.controller;


import com.hlx.entity.Product;
import com.hlx.manager.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/products")
public class ProductController {
    private static Logger logger= LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;
    @ApiOperation(value = "创建产品",notes = "根据相应的业务规则添加产品信息")
    @RequestMapping(value = "/add_product",method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product){
        logger.info("创建产品，参数={}",product.toString());
        Product result=productService.addProduct(product);
        logger.info("创建产品，结果{}",result);
        return result;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Optional<Product> findOne(@PathVariable String id){
        logger.info("查询单个产品，参数={}",id);
        Optional<Product> product=productService.findOne(id);
        logger.info("查询单个产品结果，结果={}",product);
        return product;
    }
    @RequestMapping(value = "query_product",method = RequestMethod.GET)
    public Page<Product> query(String ids, BigDecimal minRewardRate,BigDecimal maxRewardRate,String stutas,
                               @RequestParam(defaultValue = "0") int pageNum,@RequestParam(defaultValue = "10") int pageSize){

        logger.info("查询参数，ids={},minRewardRate={},maxRewardRate={},stutas={},pageNum={},pageSize={}");

        List<String> idList=null,statusList=null;
        if (!StringUtils.isEmpty(ids)){
            idList= Arrays.asList(ids.split(","));
        }
        if (!StringUtils.isEmpty(stutas)){
            statusList=Arrays.asList(stutas.split(","));
        }
        Pageable pageable=new PageRequest(pageNum,pageSize);
        Page<Product>  page= productService.query(idList,minRewardRate,maxRewardRate,statusList,pageable);
        logger.info("查询结果，结果={}",page);
        return page;
    }
}
