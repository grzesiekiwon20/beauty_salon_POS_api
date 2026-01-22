package com.beautysalon.product.dto;


import com.beautysalon.category.Category;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ProductRequest
 {
         @NonNull
         @Size(min = 3, max = 100, message = "Name has to be longer than 2 characters and shorter or equal 100")
        private String name;
         @NonNull
         @Size(min = 6, max = 200)
         private String description;
         @NonNull
         private BigDecimal price;
         private double discount;
         private double specialPrice;
         private Integer stockQuantity;
         @NonNull
         private Category category;
         private String image;
}
