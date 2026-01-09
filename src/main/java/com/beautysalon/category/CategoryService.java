package com.beautysalon.category;

import com.beautysalon.category.dto.CategoryRequest;
import com.beautysalon.category.dto.CategoryResponse;

import java.util.List;


public interface CategoryService {

    Long save(CategoryRequest request) ;
    List<CategoryResponse> getAllCategories() ;

    List<CategoryResponse> findCategoriesBySubcategory(SubCategory subCategory);
    CategoryResponse findCategoryResponseById(Long categoryId);

}
