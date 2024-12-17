package com.beautysalon.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.lang.NonNull;


public record ServiceRequest(

        Long id,
        @NonNull
                @NotBlank
                @Size(min = 3, max = 25, message = "Number of characters allowed between 3-25")
        String name,
        String description,
        @NonNull
        Double price,
        @NonNull
        String duration,
        String image ) {
}
