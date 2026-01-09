package com.beautysalon.category;

import com.beautysalon.category.dto.CategoryRequest;
import com.beautysalon.category.dto.CategoryResponse;
import org.springframework.stereotype.Component;


@Component
public class CategoryMapper {

    public CategoryResponse mapCategoryResponse(Category category){
        return CategoryResponse.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .subCategory(category.getSubCategory())
                .build();
    }
    public Category mapCategory(CategoryRequest categoryRequest){
        return Category.builder()
                .name(categoryRequest.getName())
                .subCategory(categoryRequest.getSubCategory())
                .build();
    }
}
