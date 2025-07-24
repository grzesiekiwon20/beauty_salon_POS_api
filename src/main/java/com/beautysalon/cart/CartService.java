package com.beautysalon.cart;


import com.beautysalon.cart.dto.CartResponse;
import com.beautysalon.cartitem.CartItem;
import com.beautysalon.cartitem.CartItemRepository;
import com.beautysalon.cartitem.CartItemService;
import com.beautysalon.customer.CustomerRepository;
import com.beautysalon.customer.CustomerService;
import com.beautysalon.product.InventoryStatus;
import com.beautysalon.product.Product;
import com.beautysalon.product.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemService cartItemService;
    private final CartMapper cartMapper;

    public CartService(CartRepository cartRepository, CustomerRepository customerRepository, CustomerService customerService, ProductRepository productRepository, CartItemRepository cartItemRepository, CartItemService cartItemService, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartItemService = cartItemService;
        this.cartMapper = cartMapper;
    }


    public Long createCart(String sessionId) {
        Cart existingCart = cartRepository.findBySessionId(sessionId);
        if (existingCart != null) {
            return existingCart.getId();
        } else {
            Cart cart = new Cart();
            cart.setSessionId(sessionId);
            return cartRepository.save(cart).getId();
        }
    }

    public CartResponse getCartBySessionId(String sessionId) {
        Cart cart = cartRepository.findBySessionId(sessionId);
        if (cart != null) {
            return cartMapper.mapCart(cart);
        } else {
            throw new EntityNotFoundException("Cart not found for sessionId: " + sessionId);
        }
    }

    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }


    @Transactional
    public CartResponse updateCart(Long productId, Integer quantity, String sessionId) {
        Cart cart = cartRepository.findBySessionId(sessionId);
        if (cart == null) {
            throw new EntityNotFoundException("Cart Not Found for sessionId: " + sessionId);
        }
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("No product found with id: " + productId));

        if (Objects.equals(product.getInventoryStatus(), InventoryStatus.OutOfStock)) {
            throw new RuntimeException("Product is out of stock!");
        }
        if (product.getStockQuantity() < quantity) {
            throw new RuntimeException("You can't add more products to cart than there is available!");
        }
        Long cartItemId = cartItemService.createCartItem(cart.getId(), product.getId(), quantity);

        CartItem cartItem = cartItemRepository
                .findById(cartItemId)
                .orElseThrow(
                        () -> new NoSuchElementException("No cart item found")
                );

        List<CartItem> cartProductList = cart.getCartItems();
        cartProductList.add(cartItem);
        cart.setCartItems(cartProductList);
        cart.setTotalPrice(getTotalPrice(cartProductList));

        Long cartId = cartRepository.save(cart).getId();

        return cartRepository
                .findById(cartId)
                .map(cartMapper::mapCart)
                .orElseThrow(
                        () -> new EntityNotFoundException("No cart found with id: " + cartId)
                );

    }

    private Double getTotalPrice(List<CartItem> cartItems) {
        double total = 0.00;
        for (CartItem cartItem : cartItems) {
            double discount = cartItem.getProduct().getDiscount() / 100;
            total += cartItem.getSubTotal() - (cartItem.getSubTotal() * discount);
        }
        return total;
    }

}
