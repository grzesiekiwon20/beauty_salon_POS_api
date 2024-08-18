package com.beautysalon.controller.dto;

import com.beautysalon.repository.model.AddressType;
import lombok.Builder;
import java.util.List;

@Builder
public record AddressResponse(
        Long addressId,
        String street,
        String city,
        String postCode,
        AddressType addressType,
        List<Long> users
) { }
