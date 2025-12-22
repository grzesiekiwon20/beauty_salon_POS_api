package com.beautysalon.category;


import com.beautysalon.category.dto.CategoryRequest;
import com.beautysalon.category.dto.CategoryResponse;
import com.beautysalon.exception.CategoryNameAlreadyExistException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.*;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;
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
    private Validator validator;

    @BeforeEach
    void setup() {

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

        ValidatorFactory factory = buildDefaultValidatorFactory();
        validator = factory.getValidator();
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
        void verifiesIfExceptionIsThrownIfCategoryRequestIsNull() {

            final CategoryRequest categoryRequest = null;

            NullPointerException exception = assertThrows(NullPointerException.class,
                    () -> categoryService.save(categoryRequest));

            assertNotNull(exception);
            assertThat(exception.getMessage())
                    .isNotNull()
                    .contains("Category request cannot be null");
        }

        @Test
        @DisplayName("Verifies if exception is thrown if category with given name already exists")
        void verifiesIfExceptionIsThrownIfCategoryWithGivenNameAlreadyExists() {
            final String existingName = "Eyelashes";
            when(categoryRepository.existsByName(existingName)).thenReturn(true);

            CategoryRequest categoryRequest = new CategoryRequest(1L, existingName, SubCategory.Services);
            CategoryNameAlreadyExistException exception = assertThrows(
                    CategoryNameAlreadyExistException.class,
                    () -> categoryService.save(categoryRequest)
            );
            assertThat(exception.getMessage())
                    .contains(existingName)
                    .contains("already exists");
        }

        @Test
        @DisplayName("Verifies if validation works properly when category request with given name is correct")
        void verifiesIfExceptionIsThrownWhenCategoryRequestWithGivenNameTooLong() {
            final String existingName = "Eyelashes";

            CategoryRequest categoryRequest = new CategoryRequest(1L, existingName, SubCategory.Services);
            Set<ConstraintViolation<CategoryRequest>> violationSet = validator.validate(categoryRequest);

            assertThat(violationSet).isEmpty();
        }

        @Test
        @DisplayName("Verifies if validation works properly when category request with given name is too long")
        void VerifiesIfValidationWorksProperlyWhenCategoryRequestWithGivenNameIsTooLong() {
            final String existingName = "Eyelashesssssssssssssssssssssssssssssss";

            CategoryRequest categoryRequest = new CategoryRequest(1L, existingName, SubCategory.Services);
            Set<ConstraintViolation<CategoryRequest>> violationSet = validator.validate(categoryRequest);

            assertThat(violationSet).isNotEmpty();
        }

        @Test
        @DisplayName("Verifies if validation works properly when category request with given name is too short")
        void verifiesIfValidationWorksProperlyWhenCategoryRequestWithGivenNameIsTooShort() {
            final String existingName = "Eyel";

            CategoryRequest categoryRequest = new CategoryRequest(1L, existingName, SubCategory.Services);
            Set<ConstraintViolation<CategoryRequest>> violationSet = validator.validate(categoryRequest);
            assertThat(violationSet).isNotEmpty();
        }

    }

    @Nested
    @DisplayName("Find category tests")
    class FindCategoryTests {

        @Test
        @DisplayName("Verifies if find category response by categoryId works properly")
        void verifiesIfFindCategoryResponseByCategoryIdWorksProperly() {

            when(categoryRepository.findById(testCategory.getId())).thenReturn(Optional.of(testCategory));
            when(mapper.mapCategoryResponse(testCategory)).thenReturn(testCategoryResponse);

            final CategoryResponse categoryResponse = categoryService.findCategoryResponseById(testCategory.getId());

            assertThat(categoryResponse).isNotNull();
            verify(categoryRepository, times(1)).findById(testCategory.getId());
            verify(mapper, times(1)).mapCategoryResponse(testCategory);
        }

        @Test
        @DisplayName("Verifies if findCategoryResponseById method throws exception when id is null")
        void verifiesIfFindCategoryResponseByIdThrowsExceptionWhenIdIsNull() {

            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> categoryService.findCategoryResponseById(null)
            );

            assertThat(exception.getMessage())
                    .isNotNull()
                    .contains("CategoryId")
                    .contains("null");
            verifyNoInteractions(mapper);
            verifyNoInteractions(categoryRepository);
        }

        @Test
        @DisplayName("Verifies if findCategoryResponseById method throws exception when category is not found")
        void verifiesIfFindCategoryResponseByIdThrowsExceptionWhenCategoryIsNotFound() {

            final Long categoryId = testCategory.getId();
            when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

            EntityNotFoundException exception = assertThrows(
                    EntityNotFoundException.class,
                    () -> categoryService.findCategoryResponseById(categoryId)
            );

            assertThat(exception.getMessage())
                    .isNotNull()
                    .contains("No category found")
                    .contains("categoryId")
                    .contains(String.valueOf(categoryId));

            verify(categoryRepository, times(1)).findById(categoryId);
            verifyNoInteractions(mapper);
        }

        @Test
        @DisplayName("Verifies if list of category responses is returned properly")
        void verifiesIfListOfCategoryResponsesIsReturnedProperly() {
            final Category category1 = testCategory;
            final Category category2 = Category.builder()
                    .id(2L)
                    .name("Other")
                    .subCategory(SubCategory.Services)
                    .build();
            final CategoryResponse testCategoryResponse1 = testCategoryResponse;
            final CategoryResponse testCategoryResponse2 = CategoryResponse.builder()
                    .categoryId(2L)
                    .name("Other")
                    .subCategory(SubCategory.Services)
                    .build();
            final List<Category> categories = Arrays.asList(category1, category2);

            when(categoryRepository.findAll()).thenReturn(categories);
            when(mapper.mapCategoryResponse(category1)).thenReturn(testCategoryResponse1);
            when(mapper.mapCategoryResponse(category2)).thenReturn(testCategoryResponse2);

            final List<CategoryResponse> responseList = categoryService.getAllCategories();

            assertThat(responseList).isNotNull().hasSize(2).containsExactly(testCategoryResponse1, testCategoryResponse2);

            verify(categoryRepository, times(1)).findAll();
            verify(mapper).mapCategoryResponse(category1);
            verify(mapper).mapCategoryResponse(category2);
            verify(mapper, times(responseList.size())).mapCategoryResponse(any(Category.class));
        }

        @Test
        @DisplayName("Should return all categories mapped when filtering by matching subCategory")
        void shouldReturnMappedCategoriesWhenAllMatchSubCategory() {
            // Arrange
            SubCategory subCategory = SubCategory.Services;

            Category category1 = testCategory; // e.g., Services
            Category category2 = Category.builder()
                    .id(2L)
                    .name("Other")
                    .subCategory(SubCategory.Services)
                    .build();

            List<Category> categoriesFromRepo = Arrays.asList(category1, category2);

            CategoryResponse response1 = testCategoryResponse;
            CategoryResponse response2 = CategoryResponse.builder()
                    .categoryId(2L)
                    .name("Other")
                    .subCategory(SubCategory.Services)
                    .build();

            when(categoryRepository.findBySubcategory(subCategory))
                    .thenReturn(categoriesFromRepo);
            when(mapper.mapCategoryResponse(category1)).thenReturn(response1);
            when(mapper.mapCategoryResponse(category2)).thenReturn(response2);

            // Act
            List<CategoryResponse> result = categoryService.findCategoriesBySubcategory(subCategory);

            // Assert
            assertThat(result)
                    .hasSize(2)
                    .containsExactlyInAnyOrder(response1, response2); // safer if order not guaranteed

            // Verify
            verify(categoryRepository).findBySubcategory(subCategory);
            verify(mapper).mapCategoryResponse(category1);
            verify(mapper).mapCategoryResponse(category2);
            verifyNoMoreInteractions(mapper);
        }

        @Test
        @DisplayName("Should return only matching categories when filtering by subCategory")
        void shouldReturnOnlyMatchingCategoriesBySubCategory() {
            // Arrange
            SubCategory subCategory = SubCategory.Services;

            Category matchingCategory = testCategory; // Services
            // Non-matching ones (just for context, not returned)
            Category coursesCategory = Category.builder()
                    .id(2L)
                    .name("Other")
                    .subCategory(SubCategory.Courses)
                    .build();
            Category shopCategory = Category.builder()
                    .id(3L)
                    .name("Nails")
                    .subCategory(SubCategory.Shop)
                    .build();

            // Only the matching one is returned by repo
            when(categoryRepository.findBySubcategory(subCategory))
                    .thenReturn(Collections.singletonList(matchingCategory));
            when(mapper.mapCategoryResponse(matchingCategory)).thenReturn(testCategoryResponse);

            // Act
            List<CategoryResponse> result = categoryService.findCategoriesBySubcategory(subCategory);

            // Assert
            assertThat(result)
                    .hasSize(1)
                    .containsExactly(testCategoryResponse);

            // Verify
            verify(categoryRepository).findBySubcategory(subCategory);
            verify(mapper, times(1)).mapCategoryResponse(matchingCategory);
            verifyNoMoreInteractions(mapper);
        }
    }

}
