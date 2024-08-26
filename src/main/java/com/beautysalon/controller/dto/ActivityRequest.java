package com.beautysalon.controller.dto;

import com.beautysalon.repository.model.ServiceType;
import com.beautysalon.repository.model.users.User;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Builder
public record ActivityRequest(
        LocalDate date,
        LocalTime startTime,
        LocalTime finishTime,
        ServiceType serviceType,
        boolean taskDone,
        List<User> users
) { }
