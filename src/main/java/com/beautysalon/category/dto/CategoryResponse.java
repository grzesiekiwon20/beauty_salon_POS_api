package com.beautysalon.category.dto;


import com.beautysalon.category.SubCategory;
import lombok.*;

@Data
@Builder
public class CategoryResponse {

    private Long categoryId;
    private String name;
    private SubCategory subCategory;
}
