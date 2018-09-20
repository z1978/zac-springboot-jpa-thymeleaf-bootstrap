package com.zac.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zac.spring.entity.Category;
import com.zac.spring.repository.CategoryRepository;

/**
 * Created by Zac
 */
@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

  //region Find methods
    //==================================================================================
    @Cacheable("cache.allCategorys")
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    
    @Cacheable(value = "cache.categoryByName", key = "#name", unless = "#result == null")
    public Category findByName(String name) {
        return categoryRepository.findByCategoryName(name);
    }
    
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public Category getCategoryById(Integer id){
        return categoryRepository.getOne(id);
    }

    @Cacheable(value = "cache.categoryById", key = "#id", unless = "#result == null")
    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }
    
    @Cacheable(value = "cache.parentCategoryId", key = "#id", unless = "#result == null")
    public List<Category> findByParentCategoryId(Integer id) {
        return categoryRepository.findByParentCategoryId(id);
    }
}
