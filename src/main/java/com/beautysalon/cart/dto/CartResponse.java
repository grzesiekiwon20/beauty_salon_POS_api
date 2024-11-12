package com.beautysalon.cart.dto;


import com.beautysalon.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {

    private Customer customer;
    private List<Long> cartItemsIds;
    private double totalPrice;
}
