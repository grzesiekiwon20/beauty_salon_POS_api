package com.beautysalon.address.dto;


import com.beautysalon.address.AddressType;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

public record AddressRequest(

        Long addressId,
        @NotEmpty
        @NonNull
        String firstLineAddress,
        String secondLineAddress,
        @NotEmpty
        @NonNull
        String city,
        @NotEmpty
        @NonNull
        String postCode,

        @NonNull
        AddressType addressType,
        Boolean current
        ) {
}
