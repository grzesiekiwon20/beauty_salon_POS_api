package com.beautysalon.category;
import com.beautysalon.category.dto.CategoryRequest;
import com.beautysalon.category.dto.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Long save(CategoryRequest request) {
        Category category =
                Category.builder()
                        .name(request.name())
                        .description(request.description())
                        .build();
        return categoryRepository.save(category).getId();
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(this::mapResponse)
                .toList();
    }



    private CategoryResponse mapResponse(Category category) {
        return CategoryResponse
                .builder()
                .categoryId(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }



}
