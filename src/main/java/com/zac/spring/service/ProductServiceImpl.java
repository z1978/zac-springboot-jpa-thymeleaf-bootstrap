package com.zac.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zac.spring.entity.Product;
import com.zac.spring.repository.ProductRepository;

@CacheConfig(cacheNames = "product")
@Service
public class ProductServiceImpl implements ProductService2 {

	@Autowired
	private ProductRepository productRepository;

	/**
	 * 新增商品
	 */
	@CachePut(key = "#product.productId")
	public Product insertProduct(Product product) {
		product = this.productRepository.save(product);
		return product;
	}

	/**
	 * 通过productId查找单个商品
	 */
	@Cacheable(key = "#productId")
	public Product getProductByProductId(Integer productId) {
		Product product = this.productRepository.getOne(productId);
		return product;
	}

	/**
	 * 通过productId修改单个商品
	 */
	@CachePut(key = "#product.productId")
	public Product updateProductByProductId(Product product) {
		return this.productRepository.save(product);
	}

	/**
	 * 通过productId删除单个商品
	 */
	@CacheEvict(key = "#productId")
	public void deleteProductByProductId(Integer productId) {
		this.productRepository.deleteById(productId);
	}

	/**
	 * 查找全部商品
	 */
	@Override
	@Cacheable(value = "#cache.allProduct")
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

}