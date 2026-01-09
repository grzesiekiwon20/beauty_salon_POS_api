package com.beautysalon.cart;


import com.beautysalon.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
class CartItem  {


    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    private Product product;

    @Min(1)
    private Integer quantity;

    private double subTotal;

}