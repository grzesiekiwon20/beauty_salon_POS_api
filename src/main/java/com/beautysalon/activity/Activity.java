package com.beautysalon.activity;

import com.beautysalon.serviceentity.Type;
import com.beautysalon.common.BaseEntity;
import com.beautysalon.user.employee.Employee;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Activity extends BaseEntity {



    private LocalDateTime date;
    private LocalTime startTime;
    private LocalTime finishTime;
    private Status status;
    private String remarks;
    private boolean taskDone;
    private double deposit;
    private boolean depositPaid;

    @OneToMany
    private List<Type> type;


    @ManyToOne
    @JoinColumn
    private Employee employee;



//    @OneToMany
//    @JoinColumn(name = "activitiyhistory_id")
//    private List<ActivityHistory> histories;

}