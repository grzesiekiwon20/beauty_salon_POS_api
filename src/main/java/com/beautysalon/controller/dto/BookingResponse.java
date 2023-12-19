package com.beautysalon.controller.dto;


import com.beautysalon.repository.model.ServiceType;
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
public class BookingResponse {

    private Integer id;
    private LocalDate date;
    private String startTime;
    private String finishTime;
    private ServiceType serviceType;
    private String clientEmail;
    private Integer clientId;

}
