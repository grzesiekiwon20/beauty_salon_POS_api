package com.beautysalon.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.NonNull;

public record UserEntityRequest(

        @Pattern(
                regexp = "^[0-9A-Za-z]{6,16}$",
                message = "Username must be 6-16 characters long and contain only letters and numbers."
        )
        @Size(min = 6,max = 16)
        @NonNull
        String username,

        @Size(min = 8)
        @NonNull
        @Pattern(
                regexp = "(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "Password must be min 8 characters long."
        )
        String password,
        @Email
        @NonNull
        String email
) {}
