package com.beautysalon.category;


import com.beautysalon.category.dto.CategoryRequest;
import com.beautysalon.category.dto.CategoryResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Category", description = "The Category Api")
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

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

}
