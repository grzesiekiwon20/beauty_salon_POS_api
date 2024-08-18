package com.beautysalon.controller.dto;

import com.beautysalon.repository.model.Activity;
import com.beautysalon.repository.model.Address;
import com.beautysalon.repository.model.UserType;
import lombok.Builder;

import java.util.List;

@Builder
public record UserRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        List<Activity> activities,
        List<Address> addresses,
        UserType userType
) { }
