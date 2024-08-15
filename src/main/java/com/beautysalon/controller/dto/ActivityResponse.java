package com.beautysalon.controller.dto;


import com.beautysalon.repository.model.ServiceType;
import com.beautysalon.repository.model.User;
import com.beautysalon.repository.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponse {

    private Long id;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime finishTime;
    private ServiceType serviceType;
    private UserType userType;
    private boolean taskDone;
    private User user;
}
