package com.beautysalon.employee.dto;

import com.beautysalon.activity.Activity;

import java.util.List;

public record EmployeeRequest (
        Long employeeId,
        String employeeName,
        Long userId,
        List<Activity> activityList){
}
