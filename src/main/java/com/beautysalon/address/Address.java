package com.beautysalon.address;


import com.beautysalon.common.BaseEntity;
import com.beautysalon.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Address extends BaseEntity{


    @NotNull
    private String firstLineAddress;
    private String secondLineAddress;
    @NotNull
    private String city;
    @NotNull
    private String postCode;
    @NotNull
    private AddressType addressType;
    private Boolean current;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    public Address(Long id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, String createdBy, String lastModifiedBy, String firstLineAddress, String secondLineAddress, String city, String postCode, AddressType addressType, Boolean current, Customer customer) {
        super(id, createdDate, lastModifiedDate, createdBy, lastModifiedBy);
        this.firstLineAddress = firstLineAddress;
        this.secondLineAddress = secondLineAddress;
        this.city = city;
        this.postCode = postCode;
        this.addressType = addressType;
        this.current = current;
        this.customer = customer;
    }

    public Address() {
    }

    public String getFirstLineAddress() {
        return firstLineAddress;
    }

    public void setFirstLineAddress(String firstLineAddress) {
        this.firstLineAddress = firstLineAddress;
    }

    public String getSecondLineAddress() {
        return secondLineAddress;
    }

    public void setSecondLineAddress(String secondLineAddress) {
        this.secondLineAddress = secondLineAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public Boolean getCurrent() {
        return current;
    }

    public void setCurrent(Boolean current) {
        this.current = current;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
