package com.beautysalon.activity.dto;

import com.beautysalon.type.Type;
import com.beautysalon.user.User;
import com.beautysalon.employee.Employee;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;


@Builder
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
