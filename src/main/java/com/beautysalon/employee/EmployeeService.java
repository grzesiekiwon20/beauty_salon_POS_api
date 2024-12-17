package com.beautysalon.employee;


import com.beautysalon.config.KeycloakAdminService;
import com.beautysalon.employee.dto.EmployeeResponse;
import com.beautysalon.customer.Customer;
import com.beautysalon.customer.CustomerService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeService {

    private final EmployeeMapper mapper;
    private final EmployeeRepository employeeRepository;
    private final KeycloakAdminService keycloakAdminService;

    public EmployeeService(EmployeeMapper mapper, EmployeeRepository employeeRepository, KeycloakAdminService keycloakAdminService) {
        this.mapper = mapper;
        this.employeeRepository = employeeRepository;
        this.keycloakAdminService = keycloakAdminService;
    }


    public Long saveEmployee(String id) {
        UserRepresentation userRepresentation = keycloakAdminService.getUserById(id);
        if (userRepresentation != null) {
            Employee employee = new Employee();
            employee.setFirstName(userRepresentation.getFirstName());
            employee.setLastName(userRepresentation.getLastName());
            employee.setPhoneNumber(userRepresentation.getAttributes().get("phone_number").getFirst());
            employee.setUserKeycloakId(userRepresentation.getId());
            employee.setActivities(new ArrayList<>());
            return employeeRepository.save(employee).getId();
        } else {
            throw new NullPointerException("No user found with Id " + id);
        }
    }

    public List<EmployeeResponse> findAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(mapper::map).collect(Collectors.toList());
    }


}
