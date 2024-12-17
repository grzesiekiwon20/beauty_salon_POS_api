package com.beautysalon.cart.dto;


import com.beautysalon.cartitem.dto.CartItemResponse;

import java.util.List;

public class CartResponse {

    private List<CartItemResponse> cartItems;
    private double totalPrice;

    public CartResponse(List<CartItemResponse> cartItems, double totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public CartResponse() {
    }

    public List<CartItemResponse> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemResponse> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
