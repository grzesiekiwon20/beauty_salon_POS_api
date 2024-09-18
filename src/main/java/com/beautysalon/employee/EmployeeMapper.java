package com.beautysalon.employee;

import com.beautysalon.common.BaseEntity;
import com.beautysalon.employee.dto.EmployeeResponse;
import com.beautysalon.employee.dto.EmployeeRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public EmployeeResponse map(Employee employee){
        return  EmployeeResponse.builder()
                .id(employee.getId())
                .userId(employee.getUserId())
                .activities(employee.getActivities().stream().map(BaseEntity::getId).collect(Collectors.toList()))
                .build();
    }
    public Employee map(EmployeeRequest employeeRequest){
        return Employee.builder()
                .id(employeeRequest.employeeId())
                .userId(employeeRequest.userId())
                .activities(new ArrayList<>())
                .build();
    }
}
