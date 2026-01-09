package com.beautysalon.category.dto;


import com.beautysalon.category.SubCategory;
import jakarta.validation.constraints.Size;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryRequest {

        @NonNull
        @Size(min = 5, max = 35, message = "Name can not be shorter than 3 and longer than 35 characters")
        private String name;
        @NonNull
        private SubCategory subCategory;
}
