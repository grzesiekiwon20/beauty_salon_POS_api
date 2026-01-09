package com.beautysalon.category;

import com.beautysalon.category.dto.CategoryRequest;
import com.beautysalon.category.dto.CategoryResponse;
import com.beautysalon.exception.CategoryNameAlreadyExistException;
import jakarta.persistence.EntityNotFoundException;
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
        if(categoryRepository.existsByName(request.getName())){
            throw new CategoryNameAlreadyExistException("Category with name: " + request.getName() + " already exists!");
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

    public List<CategoryResponse> findCategoriesBySubcategory(final SubCategory subCategory) {
        final List<Category> categoryList = categoryRepository.findBySubcategory(subCategory);
        return categoryList.stream().map(mapper::mapCategoryResponse).toList();
    }



    public CategoryResponse findCategoryResponseById(Long categoryId) {
        if(categoryId == null){
            throw new IllegalArgumentException("CategoryId cannot be null.");
        }
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("No category found with categoryId: " + categoryId));
        return mapper.mapCategoryResponse(category);
    }
}
