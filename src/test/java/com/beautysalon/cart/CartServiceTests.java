package com.beautysalon.cart;


import com.beautysalon.category.Category;
import com.beautysalon.category.SubCategory;
import com.beautysalon.product.Product;
import com.beautysalon.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CartServiceTests {


    @InjectMocks
    private CartService cartService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private Authentication authentication;


    private CartItem cartItem;
    private List<CartItem> cart;
    private Product product;


    @BeforeEach
    void setup() {
        this.product = Product.builder()
                .name("Product")
                .description("Product description")
                .price(new BigDecimal("0.00"))
                .stockQuantity(1)
                .image("dsdfasdfasdfasdfasdf")
                .discount(0.00)
                .specialPrice(0.00)
                .category(new Category("Product category", SubCategory.Shop))
                .build();
        this.cartItem = CartItem.builder()
                .product(this.product)
                .unitPrice(new BigDecimal("10.0"))
                .quantity(1)
                .username("username")
                .build();
        this.cart = new ArrayList<>();
    }


    @Test
    @DisplayName("shouldAddCartItemToCartSuccessfully")
    void shouldAddCartItemToCartSuccessfully() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.of(this.product));

        final List<CartItem> cartItems = cartService.addItem(this.cart, productId, 1, authentication);

        assertNotNull(cartItems);
        assertThat(cartItems).isNotEmpty();
        verify(productRepository, times(1)).findById(productId);
    }
}
