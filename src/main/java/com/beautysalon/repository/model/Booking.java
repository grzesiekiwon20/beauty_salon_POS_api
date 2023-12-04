package com.beautysalon.repository.model;

import jakarta.persistence.*;
import lombok.*;


@ToString
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue
    @Column(name = "BOOKING_ID")
    private Integer id;
    @Column(name = "DATE")
    private String date;
    @Column(name = "START_TIME")
    private String startTime;
    @Column(name = "END_TIME")
    private String endTime;
    @Column(name = "SERVICE_TYPE")
    private String serviceType;

    @Column(name = "USER_ID")
    private Integer clientId;

}