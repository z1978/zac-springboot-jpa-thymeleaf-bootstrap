package com.zac.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zac.spring.entity.Product;

/**
 * Created by Zac
 */
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product>  {
	Product findByName(String name);
	List<Product> findByCategoryId(int CategoryId);
}
