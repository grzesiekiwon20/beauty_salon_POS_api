package com.beautysalon.product.dto;

import com.beautysalon.category.Category;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Base64;

@Builder
public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        double discount,
        double specialPrice,
        byte[] image,
        Category category,
        Integer stockQuantity
) {
    public String imageBase64() {
        if (image == null || image.length == 0) {
            return null;
        }
        return Base64.getEncoder().encodeToString(image);
    }
}
