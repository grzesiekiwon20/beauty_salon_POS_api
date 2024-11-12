package com.beautysalon.cartitem.dto;

public record CartItemRequest(
        Integer quantity,
        double discount,
        double productPrice
) {
}
