package com.beautysalon.address;


import com.beautysalon.common.BaseEntity;
import com.beautysalon.customer.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Address extends BaseEntity{


    @NotBlank
    private String firstLineAddress;
    private String secondLineAddress;
    @NotBlank
    private String city;
    @NotBlank
    private String postCode;
    @NotBlank
    private AddressType addressType;
    private Boolean current;

    @ManyToMany(mappedBy = "addresses")
    private List<Customer> customers;

    public Address(Long id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, String createdBy, String lastModifiedBy, String firstLineAddress, String secondLineAddress, String city, String postCode, AddressType addressType, Boolean current, List<Customer> customers) {
        super(id, createdDate, lastModifiedDate, createdBy, lastModifiedBy);
        this.firstLineAddress = firstLineAddress;
        this.secondLineAddress = secondLineAddress;
        this.city = city;
        this.postCode = postCode;
        this.addressType = addressType;
        this.current = current;
        this.customers = customers;
    }

    public Address() {
    }

    public @NotBlank String getFirstLineAddress() {
        return firstLineAddress;
    }

    public void setFirstLineAddress(@NotBlank String firstLineAddress) {
        this.firstLineAddress = firstLineAddress;
    }

    public String getSecondLineAddress() {
        return secondLineAddress;
    }

    public void setSecondLineAddress(String secondLineAddress) {
        this.secondLineAddress = secondLineAddress;
    }

    public @NotBlank String getCity() {
        return city;
    }

    public void setCity(@NotBlank String city) {
        this.city = city;
    }

    public @NotBlank String getPostCode() {
        return postCode;
    }

    public void setPostCode(@NotBlank String postCode) {
        this.postCode = postCode;
    }

    public @NotBlank AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(@NotBlank AddressType addressType) {
        this.addressType = addressType;
    }

    public Boolean getCurrent() {
        return current;
    }

    public void setCurrent(Boolean current) {
        this.current = current;
    }

    public List<Customer> getUsers() {
        return customers;
    }

    public void setUsers(List<Customer> customers) {
        this.customers = customers;
    }
}
