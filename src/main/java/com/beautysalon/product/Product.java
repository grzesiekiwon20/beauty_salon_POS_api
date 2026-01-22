package com.beautysalon.product;


import com.beautysalon.category.Category;
import com.beautysalon.common.BaseEntity;
import com.beautysalon.file.FileUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Base64;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
public  class Product extends BaseEntity  {


    private String name;
    private String description;
    private BigDecimal price;
    private String image;
    private double discount;
    private double specialPrice;
    private Integer stockQuantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;


    public String imageBase64() {
        if (image == null || image.isEmpty()) {
            return null;
        }
        return Base64.getEncoder().encodeToString(FileUtils.readFileFromLocation(image));
    }

}
