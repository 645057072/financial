package com.hlx.manager.repositories;

import com.hlx.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product,String>,JpaSpecificationExecutor<Product> {

}

