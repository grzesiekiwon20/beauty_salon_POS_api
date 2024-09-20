package com.beautysalon.activity.dto;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ActivityResponse {

    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime finishTime;
    private String remarks;
    private boolean taskDone;
    private double deposit;
    private boolean depositPaid;
    private Long employeeId;
    private Long userId;
    private Long typeId;
}
