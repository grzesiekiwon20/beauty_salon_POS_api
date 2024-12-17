package com.beautysalon.activity;

import com.beautysalon.common.BaseEntity;
import com.beautysalon.employee.Employee;
import com.beautysalon.service.Service;
import com.beautysalon.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "activities")
public class Activity extends BaseEntity {

    @NonNull
    private LocalDate date;
    @NonNull
    private LocalTime startTime;
    private LocalTime finishTime;
    private String remarks;
    private boolean taskDone;
    private double deposit;
    private boolean depositPaid;

    @ManyToOne
    @JsonIgnore
    private Customer customer;
    @ManyToOne
    @JsonIgnore
    private Employee employee;

    @ManyToOne
    private Service service;

    public Activity(Long id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, String createdBy, String lastModifiedBy, @NonNull LocalDate date, @NonNull LocalTime startTime, LocalTime finishTime, String remarks, boolean taskDone, double deposit, boolean depositPaid, Customer customer, Employee employee, Service service) {
        super(id, createdDate, lastModifiedDate, createdBy, lastModifiedBy);
        this.date = date;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.remarks = remarks;
        this.taskDone = taskDone;
        this.deposit = deposit;
        this.depositPaid = depositPaid;
        this.customer = customer;
        this.employee = employee;
        this.service = service;
    }

    public Activity() {
    }

    @NonNull
    public LocalDate getDate() {
        return date;
    }

    public void setDate(@NonNull LocalDate date) {
        this.date = date;
    }

    @NonNull
    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(@NonNull LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalTime finishTime) {
        this.finishTime = finishTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isTaskDone() {
        return taskDone;
    }

    public void setTaskDone(boolean taskDone) {
        this.taskDone = taskDone;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public boolean isDepositPaid() {
        return depositPaid;
    }

    public void setDepositPaid(boolean depositPaid) {
        this.depositPaid = depositPaid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}