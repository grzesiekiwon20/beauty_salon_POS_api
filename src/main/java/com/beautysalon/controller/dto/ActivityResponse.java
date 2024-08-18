package com.beautysalon.controller.dto;

import com.beautysalon.repository.model.ServiceType;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Builder
public record ActivityResponse (
        Long id,
        LocalDate date,
        LocalTime startTime,
        LocalTime finishTime,
        ServiceType serviceType,
        boolean taskDone,
        List<Long>users
){ }
