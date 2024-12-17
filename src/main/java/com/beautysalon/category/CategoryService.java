package com.beautysalon.category;

import com.beautysalon.category.dto.CategoryRequest;
import com.beautysalon.category.dto.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Long save(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        category.setSubCategory(request.subCategory());
        return categoryRepository.save(category).getId();
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(this::mapResponse)
                .toList();
    }

    public List<CategoryResponse> findCategoriesBySubcategory(SubCategory subCategory) {
        List<Category> categoryList = categoryRepository.findBySubcategory(subCategory);
        return categoryList.stream().map(this::mapResponse).toList();
    }

    private CategoryResponse mapResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategoryId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setDescription(category.getDescription());
        categoryResponse.setSubCategory(category.getSubCategory());
        return categoryResponse;
    }


    public CategoryResponse findCategoriesById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NullPointerException("No category found with id: " + categoryId));
        return mapResponse(category);
    }
}
