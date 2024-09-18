package com.beautysalon.address.dto;


import com.beautysalon.user.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.NonNull;

public record AddressRequest(

        Long addressId,
        @NonNull
        @NotEmpty
        String street,
        @NonNull
        @NotEmpty
        String city,
        @NonNull
        @NotEmpty
        String postCode,
        @NonNull
        @NotEmpty
        String addressType,
        Long userId) {
}
