package com.beautysalon.cart;


import com.beautysalon.cart.dto.CartResponse;
import com.beautysalon.common.BaseEntity;
import com.beautysalon.customer.Customer;
import com.beautysalon.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;


    public Long saveCart(Authentication connectedUser) {
        if (customerRepository.findByUserId(connectedUser.getName()) == null) {
            Customer customer = new Customer();
            customer.setUserId(connectedUser.getName());
            customerRepository.save(customer);
        }
        Customer customer = customerRepository.findByUserId(connectedUser.getName());
        if(cartRepository.findByCustomerId(customer.getId()) != null){
            throw new RuntimeException("Cart for logged in customer already created!");
        }
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setCartItems(new ArrayList<>());
        cart.setTotalPrice(0.00);
        return cartRepository.save(cart).getId();
    }

    public CartResponse findCartResponse(Long customerId){
         if(cartRepository.findByCustomerId(customerId) != null){
              Cart cart = cartRepository.findByCustomerId(customerId);
             return CartResponse
                     .builder()
                     .customer(cart.getCustomer())
                     .cartItemsIds(cart.getCartItems().stream().map(BaseEntity::getId).toList())
                     .build();

         }else {
             throw new NullPointerException("No cart found for customerId: " + customerId);
         }
    }
}
