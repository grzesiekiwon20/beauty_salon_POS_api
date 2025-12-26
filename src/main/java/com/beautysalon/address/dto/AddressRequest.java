package com.beautysalon.address.dto;


import com.beautysalon.address.AddressType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.NonNull;


@Builder
public record AddressRequest(

        @NonNull
        @Size(min = 2, message = "Street name has to ba at least 2 characters long")
        @NotEmpty
        String firstLineAddress,
        String secondLineAddress,
        @NonNull
        @Size(min= 3, message = "City has to be at least 3 characters long")
        String city,
        @NotEmpty
        @NonNull
        @Pattern(regexp = "^[A-Za-z0-9\\-]{2,10}$")
        String postCode,

        @NonNull
        AddressType addressType
        ) {
}
