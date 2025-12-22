package com.beautysalon.category;


import com.beautysalon.category.dto.CategoryRequest;
import com.beautysalon.category.dto.CategoryResponse;
import com.beautysalon.exception.CategoryNameAlreadyExistException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper mapper;

    private Category testCategory;
    private CategoryRequest testCategoryRequest;
    private CategoryResponse testCategoryResponse;

    @BeforeEach
    void setup(){

        final String categoryName = "Eyelashes";
        final Long id = 1L;
        final SubCategory subCategory = SubCategory.Services;
        this.testCategory = Category.builder()
                .id(id).name(categoryName).subCategory(subCategory)
                .build();
        this.testCategoryRequest = CategoryRequest.builder()
                .name(categoryName).subCategory(subCategory)
                .build();
        this.testCategoryResponse = CategoryResponse.builder()
                .categoryId(id).name(categoryName).subCategory(subCategory)
                .build();
    }

    @Nested
    @DisplayName("Create category tests")
    class CreateCategoryTests {

        @Test
        @DisplayName("Verifies if category is saved successfully")
        void testsIfCategoryIsSavedSuccessfully() {

            when(mapper.mapCategory(testCategoryRequest)).thenReturn(testCategory);
            when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);

            final Long id = categoryService.save(testCategoryRequest);

            assertNotNull(id);
            assertEquals(testCategory.getId(), id);
            verify(mapper, times(1)).mapCategory(testCategoryRequest);
            verify(categoryRepository, times(1)).save(testCategory);

        }

        @Test
        @DisplayName("Verifies if exception is thrown if categoryRequest is null")
        void verifiesIfExceptionIsThrownIfCategoryRequestIsNull(){

            final CategoryRequest categoryRequest = null;

            NullPointerException exception = assertThrows(NullPointerException.class,
                    ()-> categoryService.save(categoryRequest));

            assertNotNull(exception);
            assertThat(exception.getMessage())
                    .isNotNull()
                    .contains("Category request cannot be null");
        }

        @Test
        @DisplayName("Verifies if exception is thrown if category with given name already exists")
        void verifiesIfExceptionIsThrownIfCategoryWithGivenNameAlreadyExists(){
            final String existingName = "Eyelashes";
            when(categoryRepository.existsByName(existingName)).thenReturn(true);

            CategoryRequest categoryRequest = new CategoryRequest(1L, existingName, SubCategory.Services);
            CategoryNameAlreadyExistException exception = assertThrows(
                    CategoryNameAlreadyExistException.class,
                    ()-> categoryService.save(categoryRequest)
            );
            assertThat(exception.getMessage())
                    .contains(existingName)
                    .contains("already exists");
        }
    }
}
