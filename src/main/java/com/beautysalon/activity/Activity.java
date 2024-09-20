package com.beautysalon.activity;

import com.beautysalon.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Activity extends BaseEntity {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime finishTime;
    private String remarks;
    private boolean taskDone;
    private double deposit;
    private boolean depositPaid;

    private Long employeeId;
    private Long userId;
    private Long typeId;




//    @OneToMany
//    @JoinColumn(name = "activitiyhistory_id")
//    private List<ActivityHistory> histories;

}