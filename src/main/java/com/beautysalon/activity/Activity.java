package com.beautysalon.activity;

import com.beautysalon.common.BaseEntity;


import com.beautysalon.serviceentity.ServiceEntity;
import com.beautysalon.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "activities")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity extends BaseEntity {

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    private LocalTime startTime;
    private String remarks;
    private boolean taskDone;
    private double deposit;
    private boolean depositPaid;


    @ManyToOne
    private ServiceEntity serviceEntity;

    @ManyToMany(mappedBy = "activities", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<UserEntity> userEntityList;

}