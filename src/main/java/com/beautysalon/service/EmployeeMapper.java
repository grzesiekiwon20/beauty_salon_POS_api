package com.beautysalon.service;

import com.beautysalon.controller.dto.EmployeeRequest;
import com.beautysalon.controller.dto.EmployeeResponse;
import com.beautysalon.repository.model.Booking;
import com.beautysalon.repository.model.Employee;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public EmployeeResponse map(Employee employee) {
        return EmployeeResponse
                .builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .tasks(employee
                        .getTasks()
                        .stream()
                        .map(Booking::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    public Employee map(EmployeeRequest employeeRequest) {
        return Employee
                .builder()
                .name(employeeRequest.getName())
                .email(employeeRequest.getEmail())
                .build();
    }
}
