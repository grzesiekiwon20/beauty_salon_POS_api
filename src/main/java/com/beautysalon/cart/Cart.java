package com.beautysalon.cart;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "cart")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Cart {

    @Id
    @NonNull
    private String cartId;

    @OneToMany(mappedBy = "cart", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<CartItem> cartItems;

    private double total;

}