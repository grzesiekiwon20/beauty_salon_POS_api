package com.beautysalon.employee;


import com.beautysalon.activity.Activity;
import com.beautysalon.common.BaseEntity;

import com.beautysalon.customer.Customer;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "employees")
@EntityListeners(AuditingEntityListener.class)
public class Employee extends BaseEntity {

    private String userKeycloakId;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Activity> activities;

    public Employee(Long id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, String createdBy, String lastModifiedBy, String userKeycloakId, String firstName, String lastName, String phoneNumber, List<Activity> activities) {
        super(id, createdDate, lastModifiedDate, createdBy, lastModifiedBy);
        this.userKeycloakId = userKeycloakId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.activities = activities;
    }

    public Employee() {
    }

    public String getUserKeycloakId() {
        return userKeycloakId;
    }

    public void setUserKeycloakId(String userKeycloakId) {
        this.userKeycloakId = userKeycloakId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
