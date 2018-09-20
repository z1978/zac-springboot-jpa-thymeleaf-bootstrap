package com.zac.spring.service;

import java.util.List;

import com.zac.spring.entity.Product;

public interface ProductService2 {

	/**
	 * 新增商品
	 */
	public Product insertProduct(Product product);

	/**
	 * 通过id查找单个商品
	 */
	public Product getProductByProductId(Integer productId);

	/**
	 * 通过id修改单个商品
	 */
	public Product updateProductByProductId(Product product);

	/**
	 * 通过id删除单个商品
	 */
	public void deleteProductByProductId(Integer productId);
	
	/**
	 * 查找全部商品
	 */
	public List<Product> findAll();

}
