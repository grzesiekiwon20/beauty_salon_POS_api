package com.beautysalon.cart;


import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class CartResponse {

    private String cartId;
    private Set<CartItem> cartItemSet;
    private Double total;
}
