package com.beautysalon.controller.dto;


import com.beautysalon.repository.model.Client;
import com.beautysalon.repository.model.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {

    private Integer id;
    private LocalDate date;
    private String clientEmail;
    private ServiceType serviceType;
    private Integer clientId;

}
