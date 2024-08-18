package com.beautysalon.controller.dto;

import com.beautysalon.repository.model.UserType;
import lombok.Builder;

import java.util.List;

@Builder
public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber ,
        UserType userType,
        List<Long> addresses,
        List<Long> activities
) { }
