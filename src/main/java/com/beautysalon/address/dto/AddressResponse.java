package com.beautysalon.address.dto;

import com.beautysalon.address.AddressType;
import com.beautysalon.user.UserEntity;
import lombok.Builder;

import java.util.Set;

@Builder
public record AddressResponse(
        Long id,
        String firstLineAddress,
        String secondLineAddress,
        String city,
        String postCode,
        AddressType addressType,
        Set<String> userEntitySet
){}
