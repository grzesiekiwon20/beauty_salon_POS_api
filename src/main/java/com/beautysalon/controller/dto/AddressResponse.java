package com.beautysalon.controller.dto;


import com.beautysalon.repository.model.AddressType;
import com.beautysalon.repository.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AddressResponse {

    private Long addressId;
    private String street;
    private String city;
    private String postCode;
    private AddressType addressType;
    private User user;

}
