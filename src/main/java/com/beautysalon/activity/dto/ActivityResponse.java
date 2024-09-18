package com.beautysalon.activity.dto;

import com.beautysalon.type.Type;
import com.beautysalon.user.User;
import com.beautysalon.employee.Employee;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
