package com.beautysalon.category;


import com.beautysalon.category.dto.CategoryRequest;
import com.beautysalon.category.dto.CategoryResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Category", description = "The Category Api")
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createCategory(
            @RequestBody CategoryRequest categoryRequest
    ){
        return ResponseEntity.ok(service.save(categoryRequest));
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        return ResponseEntity.ok(service.getAllCategories());
    }
    @GetMapping("/subCategory")
    public ResponseEntity<List<CategoryResponse>> getCategoriesBySubcategory(
            @RequestParam SubCategory subCategory
    ){
        return ResponseEntity.ok(service.findCategoriesBySubcategory(subCategory));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategoriesById(
            @PathVariable Long categoryId
    ){
        return ResponseEntity.ok(service.findCategoriesById(categoryId));
    }

}
