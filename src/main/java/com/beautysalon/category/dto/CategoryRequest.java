package com.beautysalon.category.dto;


import com.beautysalon.category.SubCategory;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.lang.NonNull;

public record CategoryRequest(
        Long id,
        @NotEmpty
        @Size(min = 5, max = 35)
        String name,
        String description,
        @NonNull
        SubCategory subCategory
) {
}
