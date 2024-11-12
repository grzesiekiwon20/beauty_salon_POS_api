package com.beautysalon.employee.dto;

import com.beautysalon.activity.Activity;

import java.util.List;

public record EmployeeRequest (
        String userId,
        List<Activity> activityList){}
