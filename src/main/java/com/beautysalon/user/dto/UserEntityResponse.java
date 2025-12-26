package com.beautysalon.user.dto;


import lombok.Builder;
import java.util.Set;

@Builder
public record UserEntityResponse(
        String userId,
        String username,
        String password,
        String email,
        boolean enabled,
        boolean locked,
        Set<Long> roles,
        Set<Long> addresses
)
{ }
