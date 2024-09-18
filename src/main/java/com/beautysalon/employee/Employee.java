package com.beautysalon.employee;


import com.beautysalon.activity.Activity;
import com.beautysalon.common.BaseEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
@EntityListeners(AuditingEntityListener.class)
public class Employee extends BaseEntity{


    private Long userId;

    @OneToMany(mappedBy = "employeeId")
    private List<Activity> activities;

}
