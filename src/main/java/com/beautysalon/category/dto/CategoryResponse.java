package com.beautysalon.category.dto;


import com.beautysalon.category.SubCategory;
import lombok.*;

@Builder
public record CategoryResponse (
        Long categoryId,
        String name,
        SubCategory subCategory
){ }
