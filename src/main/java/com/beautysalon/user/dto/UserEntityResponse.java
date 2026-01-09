package com.beautysalon.user.dto;


import com.beautysalon.role.Role;
import lombok.Builder;
import java.util.Set;

@Builder
public record UserEntityResponse(
        String userId,
        String username,
        String password,
        String email,
        String fullName,
        String phone,
        boolean enabled,
        boolean locked,
        Set<Role> roles,
        Set<Long> addresses
)
{ }
