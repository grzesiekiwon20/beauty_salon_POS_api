package com.beautysalon.serviceentity.dto;


import lombok.Builder;

import java.time.LocalTime;
import java.util.Base64;

@Builder
public record ServiceEntityResponse(
        Long id,
        String name,
        String categoryName,
        Long categoryId,
        String description,
        Double price,
        LocalTime duration,
        byte[] image
) {
    public String imageBase64() {
        if (image == null || image.length == 0) {
            return null;
        }
        return Base64.getEncoder().encodeToString(image);
    }
}
