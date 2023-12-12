package com.beautysalon.controller.dto;


import com.beautysalon.repository.model.Client;
import com.beautysalon.repository.model.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

    private LocalDate date;
    private ServiceType serviceType;
    private String clientEmail;
    private Integer clientId;

}
