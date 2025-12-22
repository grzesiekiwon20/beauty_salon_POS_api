package com.beautysalon.category;

import com.beautysalon.category.dto.CategoryRequest;
import com.beautysalon.category.dto.CategoryResponse;
import com.beautysalon.exception.CategoryNameAlreadyExistException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    public Long save(@Valid final CategoryRequest request) {
        if(request == null){
            throw new NullPointerException("Category request cannot be null");
        }
        if(categoryRepository.existsByName(request.name())){
            throw new CategoryNameAlreadyExistException("Category with name: " + request.name() + " already exists!");
        }
        else{
            final Category category = mapper.mapCategory(request);

            return categoryRepository.save(category).getId();
        }
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(mapper::mapCategoryResponse)
                .toList();
    }

    public List<CategoryResponse> findCategoriesBySubcategory(SubCategory subCategory) {
        List<Category> categoryList = categoryRepository.findBySubcategory(subCategory);
        return categoryList.stream().map(mapper::mapCategoryResponse).toList();
    }



    public CategoryResponse findCategoryResponseById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NullPointerException("No category found with id: " + categoryId));
        return mapper.mapCategoryResponse(category);
    }
}
