package com.beautysalon.controller.dto;

import com.beautysalon.repository.model.UserType;
import com.beautysalon.repository.model.users.LoginDetails;
import lombok.Builder;

import java.util.List;

@Builder
public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber ,
        LoginDetails loginDetails,
        UserType userType,
        List<Long> addresses,
        List<Long> activities,
        boolean enabled) { }
