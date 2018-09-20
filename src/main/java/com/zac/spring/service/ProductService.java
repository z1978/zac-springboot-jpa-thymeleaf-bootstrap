package com.zac.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zac.spring.entity.Product;
import com.zac.spring.repository.ProductRepository;

/**
 * Created by Zac
 */
@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

  //region Find methods
    //==================================================================================
    @Cacheable("cache.allProducts")
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    
    @Cacheable(value = "cache.productByName", key = "#name", unless = "#result == null")
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }
    
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public Product getProductById(Integer id){
        return productRepository.getOne(id);
    }

    @Cacheable(value = "cache.productById", key = "#id", unless = "#result == null")
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }
    
    @Cacheable(value = "cache.productByCategoryId", key = "#id", unless = "#result == null")
    public List<Product> findByCategoryId(Integer id) {
        return productRepository.findByCategoryId(id);
    }
    //==================================================================================
    //endregion

    @CacheEvict(value = {"cache.allProducts" , "cache.productByName", "cache.productById"}, allEntries = true)
    public void deleteById(Integer id) {
    	productRepository.deleteById(id);
    }
    
    @CacheEvict(value = {"cache.allProducts" , "cache.productByName", "cache.productById"}, allEntries = true)
    public void createOne(Product product) {
//    	product.setProductId(99);
    	short s;
    	s = 1;
    	product.setStatus(s);
    	product.setDelFlg(s);
    	Date nowDate = new Date();
    	product.setCreateDate(nowDate);
    	product.setUpdateDate(nowDate);
        productRepository.save(product);
    }
    
    @CacheEvict(value = {"cache.allProducts" , "cache.productByName", "cache.productById"}, allEntries = true)
    public void updateOne(Product product) {
    	Date nowDate = new Date();
    	product.setUpdateDate(nowDate);
        productRepository.save(product);
    }

    public boolean checkIfProductNameIsTaken(List<Product> allProducts, Product product, Product persistedProduct) {
        boolean productNameAlreadyExists = false;
        for (Product product1 : allProducts) {
            //Check if the product name is edited and if it is taken
            if (!product.getName().equals(persistedProduct.getName())
                    && product.getName().equals(product1.getName())) {
                productNameAlreadyExists = true;
            }
        }
        return productNameAlreadyExists;
    }
}
