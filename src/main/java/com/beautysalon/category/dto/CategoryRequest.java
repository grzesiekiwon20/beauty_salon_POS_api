package com.beautysalon.category.dto;


import com.beautysalon.category.SubCategory;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.lang.NonNull;
@Builder
public record CategoryRequest(
        Long id,
        @NotEmpty
        @Size(min = 5, max = 35)
        @NonNull
        String name,
        @NonNull
        SubCategory subCategory
) {
}
