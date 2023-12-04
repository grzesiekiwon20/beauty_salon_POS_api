package com.beautysalon.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

    private String date;
    private String startTime;
    private String endTime;
    private String serviceType;
    private Integer userId;

}
