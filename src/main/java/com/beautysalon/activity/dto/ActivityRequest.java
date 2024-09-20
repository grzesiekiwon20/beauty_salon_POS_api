package com.beautysalon.activity.dto;


import java.time.LocalDate;
import java.time.LocalTime;


public record ActivityRequest(
        Long id,
        LocalDate date,
        LocalTime startTime,
        LocalTime finishTime,
        String remarks,
        boolean taskDone,
        double deposit,
        boolean depositPaid,
        Long employeeId,
        Long typeId,
        Long userId) {}
