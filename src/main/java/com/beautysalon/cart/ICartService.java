package com.beautysalon.cart;


import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ICartService {

    List<CartItem> addItem(List<CartItem> cartItems, Long productId, Integer quantity, Authentication authentication);

    List<CartItem> clearCart(List<CartItem> cart, Authentication authentication);

    List<CartItem> mergeCarts(List<CartItem> cart, Authentication authentication, HttpSession session);



}
