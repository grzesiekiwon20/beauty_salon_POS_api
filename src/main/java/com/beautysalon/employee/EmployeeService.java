package com.beautysalon.employee;

import com.beautysalon.employee.dto.EmployeeResponse;
import com.beautysalon.user.User;
import com.beautysalon.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;
    private final UserRepository userRepository;
    private final EmployeeMapper mapper;

    public Long saveEmployee(String email) {
       User user = userRepository.findByEmail(email).orElseThrow(()->new NullPointerException("No User Found"));
       Employee employee = new Employee();
       employee.setUserId(user.getId());
       return repository.save(employee).getId();
    }
    public List<EmployeeResponse> findAllEmployees(){
        List<Employee> employees = repository.findAll();
        return employees.stream().map(mapper::map).collect(Collectors.toList());
    }


}
