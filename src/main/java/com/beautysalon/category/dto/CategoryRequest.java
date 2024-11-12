package com.beautysalon.category.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        Long id,
        @NotBlank
        @NotEmpty
        @Size(min = 5, max = 15)
        String name,
        String description
) {
}
