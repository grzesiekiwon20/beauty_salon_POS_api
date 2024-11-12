package com.beautysalon.activity.dto;


import com.beautysalon.customer.Customer;
import com.beautysalon.employee.Employee;
import com.beautysalon.type.Type;
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
    private Employee employee;
    private Customer customer;
    private Type type;
}
