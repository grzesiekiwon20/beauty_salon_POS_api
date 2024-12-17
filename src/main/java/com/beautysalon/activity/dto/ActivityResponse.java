package com.beautysalon.activity.dto;


import com.beautysalon.employee.Employee;
import com.beautysalon.customer.Customer;

import java.time.LocalDate;
import java.time.LocalTime;

public class ActivityResponse {

    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime finishTime;
    private String remarks;
    private boolean taskDone;
    private double deposit;
    private boolean depositPaid;
    private Employee employee;
    private Customer customer;
    private String serviceName;
    private byte[] activityImage;

    public ActivityResponse(Long id, LocalDate date, LocalTime startTime, LocalTime finishTime, String remarks, boolean taskDone, double deposit, boolean depositPaid, Employee employee, Customer customer, String serviceName, byte[] activityImage) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.remarks = remarks;
        this.taskDone = taskDone;
        this.deposit = deposit;
        this.depositPaid = depositPaid;
        this.employee = employee;
        this.customer = customer;
        this.serviceName = serviceName;
        this.activityImage = activityImage;
    }

    public ActivityResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public byte[] getActivityImage() {
        return activityImage;
    }

    public void setActivityImage(byte[] activityImage) {
        this.activityImage = activityImage;
    }
}
