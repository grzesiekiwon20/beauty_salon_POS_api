package com.beautysalon.user.dto;

import lombok.Builder;

@Builder
public record UserRequest(
        String username,
        String password,
        String email
) {}
