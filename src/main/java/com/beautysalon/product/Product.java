package com.beautysalon.product;


import com.beautysalon.cartitem.CartItem;
import com.beautysalon.category.Category;
import com.beautysalon.common.BaseEntity;
import com.beautysalon.orderItem.OrderItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Product extends BaseEntity {


    @NotBlank
    @Size(min = 3, message = "Product name must contain at least 3 characters")
    private String name;
    @NotBlank
    @Size(min = 6, message = "Product description must contain at least 6 characters")
    private String description;
    private double price;
    private double discount;
    private double specialPrice;
    private Integer stockQuantity;

    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<CartItem> products;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<OrderItem> orderItems;
}
