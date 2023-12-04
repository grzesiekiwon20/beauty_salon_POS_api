package com.beautysalon.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {

    private Integer id;
    private String date;
    private String startTime;
    private String endTime;
    private String serviceType;
    private Integer clientId;

}
