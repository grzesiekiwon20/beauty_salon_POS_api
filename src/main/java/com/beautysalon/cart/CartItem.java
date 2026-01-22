package com.beautysalon.cart;


import com.beautysalon.common.BaseEntity;
import com.beautysalon.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CartItem extends BaseEntity{

    @Serial
    private static final long serialVersionUID = 4046409674630971997L;

    @ManyToOne
    private Product product;

    private Integer quantity;

    private String username;

    @Column(precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Transient
    public BigDecimal getSubtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

}