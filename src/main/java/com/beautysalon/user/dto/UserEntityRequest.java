package com.beautysalon.user.dto;

import com.beautysalon.user.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntityRequest {

    @Pattern(
            regexp = "^[0-9A-Za-z]{6,16}$",
            message = "Username must be 6-16 characters long and contain only letters and numbers.")
    @Size(min = 6, max = 16)
    @NonNull
    private String username;

    @Size(min = 8)
    @NonNull

    private String password;
    @NonNull
    private String confirm;
    @Email
    @NonNull
    private String email;
    @NonNull
    @Pattern(regexp = "^[0-9]{5,12}$")
    private String phone;
    @NonNull
    private String fullName;
    @NonNull
    @Size(min = 2, message = "Street name has to ba at least 2 characters long")
    private String street;
    @NonNull
    @Size(min= 3, message = "City has to be at least 3 characters long")
    private String city;
    @NonNull
    @Pattern(regexp = "^[A-Za-z0-9\\-]{2,10}$")
    private String postCode;


}

