package com.zac.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zac.spring.entity.Category;

/**
 * Created by Zac
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category>  {
	Category findByCategoryName(String categoryName);
	List<Category> findByParentCategoryId(int parentcategoryId);
		
}
