package com.beautysalon.product.dto;


import com.beautysalon.cartitem.CartItem;
import com.beautysalon.orderItem.OrderItem;
import com.beautysalon.product.InventoryStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.lang.NonNull;

import java.util.List;

public record ProductRequest(
        @NonNull
        @Size(min = 3, max = 50, message = "Name has to be longer than 2 characters and shorter or equal 50")
        String name,
        @NonNull
        @Size(min = 6, max = 100)
        String description,
        @NonNull
        Double price,
        Double discount,
        Double specialPrice,
        @NonNull
        Integer stockQuantity,
        @NonNull
        InventoryStatus inventoryStatus,
        @NonNull
        String image,
        List<CartItem> products,
        List<OrderItem> orderItems
) {
}
