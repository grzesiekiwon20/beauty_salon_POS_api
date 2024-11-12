package com.beautysalon.cartitem;


import com.beautysalon.cart.Cart;
import com.beautysalon.common.BaseEntity;
import com.beautysalon.product.Product;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem extends BaseEntity {

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Product product;

    private Integer quantity;
    private double discount;
    private double productPrice;
}
