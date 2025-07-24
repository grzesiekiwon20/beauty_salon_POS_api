package com.beautysalon.category;


import com.beautysalon.category.dto.CategoryRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void checkIfCategorySavedProperly() {
        CategoryRequest categoryRequest = new CategoryRequest(null, "Eyelashes", "no description", SubCategory.Services);


    }
}
