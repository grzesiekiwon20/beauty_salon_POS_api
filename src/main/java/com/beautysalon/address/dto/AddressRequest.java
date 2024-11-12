package com.beautysalon.address.dto;


import com.beautysalon.address.AddressType;
import jakarta.validation.constraints.NotEmpty;
import lombok.NonNull;

public record AddressRequest(

        Long addressId,
        @NonNull
        @NotEmpty
        String firstLineAddress,
        String secondLineAddress,
        @NonNull
        @NotEmpty
        String city,
        @NonNull
        @NotEmpty
        String postCode,
        @NonNull
        AddressType addressType,
        Boolean current,
        String userId) {
}
