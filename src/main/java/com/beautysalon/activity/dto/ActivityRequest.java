package com.beautysalon.activity.dto;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ActivityRequest{

    @NonNull
    private LocalDate date;
    @NonNull
    private LocalTime startTime;
    private String remarks;
    private boolean taskDone;
    private double deposit;
    private boolean depositPaid;
    private Long serviceEntityId;
    private String employeeId;
    private String customerId;
}
