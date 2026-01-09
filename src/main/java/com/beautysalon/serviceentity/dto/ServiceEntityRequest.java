package com.beautysalon.serviceentity.dto;

import com.beautysalon.category.Category;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceEntityRequest {

    @Size(min = 3, max = 25, message = "Number of characters allowed between 3-25")
    @NonNull
    private String name;
    private String description;
    @NonNull
    private Double price;
    @NonNull
    private LocalTime duration;
    private String image;
    @NonNull
    private Category category;
}
