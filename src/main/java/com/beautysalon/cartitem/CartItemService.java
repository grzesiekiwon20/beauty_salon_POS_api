package com.beautysalon.cartitem;


import com.beautysalon.cart.Cart;
import com.beautysalon.cart.CartRepository;
import com.beautysalon.cartitem.dto.CartItemRequest;
import com.beautysalon.product.Product;
import com.beautysalon.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public Long saveCart(Long cartId, Long productId, CartItemRequest cartItemRequest) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NullPointerException("No product found with productId: " + productId));
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NullPointerException("No cart found with cartId: " + cartId));
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(cartItemRequest.quantity())
                .discount(cartItemRequest.discount())
                .productPrice(cartItemRequest.productPrice())
                .build();
        return cartItemRepository.save(cartItem).getId();
    }
}
