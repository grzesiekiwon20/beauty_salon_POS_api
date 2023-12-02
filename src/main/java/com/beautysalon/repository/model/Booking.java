package com.beautysalon.repository.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue
    @Column(name = "BOOKING_ID")
    private Long id;
    @Column(name = "DATE")
    private String date;
    @Column(name = "START_TIME")
    private String startTime;
    @Column(name = "END_TIME")
    private String endTime;
    @Column(name = "SERVICE_TYPE")
    private String serviceType;
    @Column(name = "USER_ID")
    private Long userId;

    public Booking(String date, String startTime, String endTime, String serviceType) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.serviceType = serviceType;

    }
}