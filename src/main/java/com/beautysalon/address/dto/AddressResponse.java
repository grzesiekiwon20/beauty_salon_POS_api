package com.beautysalon.address.dto;

import com.beautysalon.address.AddressType;
import lombok.Builder;

import java.util.Set;

@Builder
public record AddressResponse(
        Long id,
        String street,
        String city,
        String postCode,
        AddressType addressType,
        Set<String> userEntitySet
){}
