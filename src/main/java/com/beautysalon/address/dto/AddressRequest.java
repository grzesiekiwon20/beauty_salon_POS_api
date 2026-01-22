package com.beautysalon.address.dto;


import com.beautysalon.address.AddressType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressRequest {

    @NonNull
    @Size(min = 2, message = "Street name has to ba at least 2 characters long")
    @NotEmpty
    private String street;
    @NonNull
    @Size(min = 3, message = "City has to be at least 3 characters long")
    private String city;
    @NotEmpty
    @NonNull
    @Pattern(regexp = "^[A-Za-z0-9\\-]{2,10}$")
    private String postCode;

    private String country;

    @NonNull
    private AddressType addressType;
}
