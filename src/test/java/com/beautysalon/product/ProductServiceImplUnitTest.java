package com.beautysalon.product;


import com.beautysalon.category.Category;
import com.beautysalon.category.SubCategory;
import com.beautysalon.file.FileStorageService;
import com.beautysalon.product.dto.ProductRequest;
import com.beautysalon.product.dto.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplUnitTest {

    @InjectMocks
    private ProductServiceImpl productServiceTest;

    @Mock
    private ProductMapper mapperTest;

    @Mock
    private ProductRepository repositoryTest;

    @Mock
    private FileStorageService fileStorageServiceTest;


    private ProductRequest productRequestTest;
    private ProductResponse productResponseTest;
    private Product productTest;
    private Category categoryTest;

    @BeforeEach
    void setup() {
        Long id = 1L;
        String name = "Product";
        String description = "Product description";
        BigDecimal price = new BigDecimal("0.00");
        double discount = 0.00;
        double specialPrice = 0.00;
        String image = "encoded image string value";
        Integer stockQuantity = 1;
        byte[] imageArr = {23, 4, 23, 3, 5, 6, 7, 8, 43, 7, 85, 7, 54, 90, 4, 3, 1, 8};
//        this.categoryTest = Category.builder().id(1L).name("Category").subCategory(SubCategory.Shop).build();
        this.productRequestTest = ProductRequest.builder()
                .name(name)
                .description(description)
                .price(price)
                .image(image)
                .discount(discount)
                .stockQuantity(stockQuantity)
                .category(categoryTest)
                .specialPrice(specialPrice)
                .build();
        this.productResponseTest = ProductResponse.builder()
//                .id()
                .name(name)
                .description(description)
                .price(price)
                .image(imageArr)
                .stockQuantity(stockQuantity)
                .discount(discount)
                .specialPrice(specialPrice)
                .category(categoryTest)
                .build();
        this.productTest = Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .image(image)
                .stockQuantity(stockQuantity)
                .discount(discount)
                .specialPrice(specialPrice)
                .category(categoryTest)
                .build();

    }

    @Nested
    @DisplayName("Products creation tests")
    class ProductCreationTests {

        @Test
        @DisplayName("Should create product successfully")
        void shouldCreateProductSuccessfully() {


        }
    }
}
