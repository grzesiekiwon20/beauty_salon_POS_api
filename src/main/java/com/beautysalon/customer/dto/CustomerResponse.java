package com.beautysalon.customer.dto;

import com.beautysalon.activity.Activity;
import com.beautysalon.address.Address;
import com.beautysalon.cart.Cart;

import java.util.List;

public class CustomerResponse {

    private Long id;
    private String userKeycloakId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private List<Address> addresses;

    private Cart cart;

    private List<Activity> activities;

    public CustomerResponse(Long id, String userKeycloakId, String firstName, String lastName, String email, String phoneNumber, List<Address> addresses, Cart cart, List<Activity> activities) {
        this.id = id;
        this.userKeycloakId = userKeycloakId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.addresses = addresses;
        this.cart = cart;
        this.activities = activities;
    }

    public CustomerResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserKeycloakId() {
        return userKeycloakId;
    }

    public void setUserKeycloakId(String userKeycloakId) {
        this.userKeycloakId = userKeycloakId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
