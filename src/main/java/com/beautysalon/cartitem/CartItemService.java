package com.beautysalon.cartitem;


import com.beautysalon.cart.Cart;
import com.beautysalon.cart.CartRepository;
import com.beautysalon.cart.dto.CartResponse;
import com.beautysalon.cartitem.dto.CartItemResponse;
import com.beautysalon.product.Product;
import com.beautysalon.product.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper mapper;

    public CartItemService(ProductRepository productRepository, CartRepository cartRepository, CartItemRepository cartItemRepository, CartItemMapper mapper) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.mapper = mapper;
    }

    public Long createCartItem(Long cartId, Long productId, Integer quantity) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(
                        () -> new EntityNotFoundException("No product found with productId: " + productId)
                );
        Cart cart = cartRepository
                .findById(cartId)
                .orElseThrow(
                        () -> new EntityNotFoundException("No cart found with cartId: " + cartId)
                );
        double subTotal = quantity * product. getPrice();
        CartItem cartItem = new CartItem();
                cartItem.setCart(cart);
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity);
                cartItem.setSubTotal(subTotal);
        return cartItemRepository.save(cartItem).getId();
    }

    public Long updateQuantity(Long cartItemId, Integer quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()-> new EntityNotFoundException("Cart item not found"));

        double subTotal = quantity * cartItem.getProduct().getPrice();
        cartItem.setQuantity(quantity);
        cartItem.setSubTotal(subTotal);
        return cartItemRepository.save(cartItem).getId();
    }

    public List<CartItemResponse> findCartItemsForCart(Long cartId) {
        return cartItemRepository
                .findByCartId(cartId)
                .stream()
                .map(mapper::cartItemResponse)
                .toList();
    }

    public Long incrementByOne(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()-> new EntityNotFoundException("Cart item not found"));

        Integer quantity = cartItem.getQuantity();
        quantity++;
        double subTotal = (cartItem.getQuantity() + quantity) * cartItem.getProduct().getPrice();
        cartItem.setQuantity(quantity);
        cartItem.setSubTotal(subTotal);
        return cartItemRepository.save(cartItem).getId();
    }

    public Long decrementByOne(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()-> new EntityNotFoundException("Cart item not found"));

        Integer quantity = cartItem.getQuantity();
        quantity--;
        double subTotal = (cartItem.getQuantity() + quantity) * cartItem.getProduct().getPrice();
        cartItem.setQuantity(quantity);
        cartItem.setSubTotal(subTotal);
        return cartItemRepository.save(cartItem).getId();
    }
}
