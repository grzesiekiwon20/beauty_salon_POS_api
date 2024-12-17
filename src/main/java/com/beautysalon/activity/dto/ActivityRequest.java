package com.beautysalon.activity.dto;


import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;


public record ActivityRequest(
        @NotNull
        LocalDate date,
        @NotNull
        LocalTime startTime,
        String remarks,
        boolean taskDone,
        double deposit,
        boolean depositPaid,
        @NotNull
        Long employeeId,
        @NotNull
        Long serviceId
) {}
