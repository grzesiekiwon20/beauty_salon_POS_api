package com.beautysalon.product.dto;


import com.beautysalon.cartitem.CartItem;
import com.beautysalon.orderItem.OrderItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ProductRequest(
        @NotBlank
                @Size(min= 3)
        String name,
        @NotBlank
                @Size(min = 6)
        String description,
        double price,
        double discount,
        double specialPrice,
        Integer stockQuantity,
        String image,
        List<CartItem> products,
        List<OrderItem> orderItems
) {}
