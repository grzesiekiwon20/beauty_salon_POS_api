package com.beautysalon.employee.dto;

import com.beautysalon.activity.Activity;
import com.beautysalon.user.User;

import java.util.List;

public record EmployeeRequest (
        Long employeeId,
        String employeeName,
        Long userId,
        List<Activity> activityList){
}
