package com.beautysalon.employee;

import com.beautysalon.config.KeycloakAdminService;
import com.beautysalon.employee.dto.EmployeeResponse;
import com.beautysalon.user.User;
import com.beautysalon.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    private final UserService userService;

    public Long saveEmployee(String id) {
        List<User> users = userService.getUserList();
        Employee employee = new Employee();
        for (User user : users) {
            if (user.getId().equals(id)) {
                employee.setUserId(user.getId());
            }
        }
        return repository.save(employee).getId();
    }

    public List<EmployeeResponse> findAllEmployees() {
        List<Employee> employees = repository.findAll();
        return employees.stream().map(mapper::map).collect(Collectors.toList());
    }


}
