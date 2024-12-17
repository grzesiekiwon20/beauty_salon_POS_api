package com.beautysalon.customer;


import com.beautysalon.cart.Cart;
import com.beautysalon.common.BaseEntity;
import com.beautysalon.customer.dto.CustomerResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomerMapper {


    public Customer map(UserRepresentation userRepresentation){
        Customer customer = new Customer();
        customer.setUserKeycloakId(userRepresentation.getId());
        customer.setFirstName(userRepresentation.getFirstName());
        customer.setLastName(userRepresentation.getLastName());
        customer.setEmail(userRepresentation.getEmail());
        customer.setPhoneNumber(userRepresentation.getAttributes().get("phone_number").getFirst());
        customer.setCart(new Cart());
        customer.setAddresses(new ArrayList<>());
        customer.setActivities(new ArrayList<>());
        return customer;
    }

    public CustomerResponse mapCustomerResponse(Customer customer){
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setUserKeycloakId(customer.getUserKeycloakId());
        customerResponse.setFirstName(customer.getFirstName());
        customerResponse.setLastName(customer.getLastName());
        customerResponse.setEmail(customer.getEmail());
        customerResponse.setPhoneNumber(customer.getPhoneNumber());
        customerResponse.setAddresses(customer.getAddresses());
        customerResponse.setCart(customer.getCart());
        customerResponse.setActivities(customer.getActivities());
        return customerResponse;
    }
}
