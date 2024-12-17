package com.beautysalon.employee;

import com.beautysalon.common.BaseEntity;
import com.beautysalon.employee.dto.EmployeeResponse;
import org.springframework.stereotype.Component;


@Component
public class EmployeeMapper {

    public EmployeeResponse map(Employee employee){
       EmployeeResponse employeeResponse = new EmployeeResponse();
       employeeResponse.setId(employee.getId());
       employeeResponse.setFirstName(employee.getFirstName());
       employeeResponse.setLastName(employee.getLastName());
       employeeResponse.setPhoneNumber(employee.getPhoneNumber());
       employeeResponse.setActivities(employee.getActivities()
               .stream().map(BaseEntity::getId).toList()
       );
       return employeeResponse;
    }
}
