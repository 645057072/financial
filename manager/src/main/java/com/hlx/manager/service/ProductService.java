package com.hlx.manager.service;

import com.hlx.entity.Product;
import com.hlx.entity.enums.ProductStatus;
import com.hlx.manager.error.ErrorEnum;
import com.hlx.manager.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    private static Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product){
        //校验数据
        LOGGER.debug("创建产品.参数:{}",product);
        checkedProduct(product);
        setDefault(product);
        Product result=productRepository.save(product);
        LOGGER.debug("保存产品，请求参数：{}",product);
        return result;
    }
//设置默认时间
    private void setDefault(Product product) {
        if (product.getCreateAt()==null){
            product.setCreateAt(new Date());
        }
        if (product.getUpdateAt()==null){
            product.setUpdateAt(new Date());
        }
        if(product.getStepAmount()==null){
            product.setStepAmount(BigDecimal.ZERO);
        }
        if(product.getLockTerm()==null){
            product.setLockTerm(0);
        }
        if (product.getStatus() == null){
            product.setStatus(ProductStatus.AUDITING.name());
        }
    }

    /*产品数据校验
* 1.产品数据不能空
* 2.收益率为0-30之内
* 3.投资步长为整数
*
* */
    private void checkedProduct(Product product) {
        LOGGER.debug("参数=={}",product.getId());
        Assert.notNull(product.getId(),ErrorEnum.ID_NO_NULL.getCode());

        Assert.isTrue(BigDecimal.ZERO.compareTo(product.getRewardRate())<0&&BigDecimal.valueOf(30).compareTo(product.getRewardRate())>=0,"收益率为0-30%之间");
        Assert.isTrue(BigDecimal.valueOf(product.getStepAmount().longValue()).compareTo(product.getStepAmount())==0,"投资步长需为整数");
    }

/*
*
* 查询单个产品信息
*
* */
    public Optional<Product> findOne(String id){
        Assert.notNull(id,"需要输入产品参数");
        LOGGER.debug("查询产品，id={}",id);
        Optional<Product> product=productRepository.findById(id);
        LOGGER.debug("查询单个产品，结果={}",product);
        return product;

    }


    /*
    * 分页查询，通过复杂条件查询产品
    *
    * */
    public Page<Product> query(List<String> idList,
                               BigDecimal minRewardRate, BigDecimal maxRewardRate,
                               List<String> statusList,
                               Pageable pageable){
        LOGGER.debug("查询参数，idList={},minRewardRate={},maxRewardRate={},statusList={},pageable={}",idList,minRewardRate,maxRewardRate,statusList,pageable);
        Specification<Product> specification=new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Expression<String> idCol=root.get("id");
                Expression<BigDecimal> rewardRateCol=root.get("rewardRate");
                Expression<String> statusCol=root.get("status");
                List<Predicate> predicates=new ArrayList<>();
                if(idList!=null&&idList.size()>0){
                    predicates.add(idCol.in(idList));
                }
                if (minRewardRate!=null&&BigDecimal.ZERO.compareTo(minRewardRate)<0){
                    predicates.add(cb.ge(rewardRateCol,minRewardRate));
                }
                if(maxRewardRate!=null&&BigDecimal.ZERO.compareTo(maxRewardRate)<0){
                    predicates.add(cb.le(rewardRateCol,maxRewardRate));
                }
                if (statusList!=null&&statusList.size()>0){
                    predicates.add(statusCol.in(statusList));
                }
                query.where(predicates.toArray(new Predicate[0]));
                return null;
            }
        };


        Page<Product> page = productRepository.findAll(specification, pageable);
        LOGGER.debug("查询结果，结果={}",page);
        return page;
    }
}
