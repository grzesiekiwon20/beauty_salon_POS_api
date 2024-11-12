package com.beautysalon.product.dto;


import com.beautysalon.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String name;
    private String description;
    private double price;
    private double discount;
    private double specialPrice;
    private Integer stockQuantity;
    private byte[] image;
    private Category category;
    private List<Long> products;
    private List<Long> orderItems;

}
