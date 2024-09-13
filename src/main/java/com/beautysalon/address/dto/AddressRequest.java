package com.beautysalon.address.dto;


public record AddressRequest(
        Long id,
        String street,
        String city,
        String postCode,
        String addressType
) {
}
