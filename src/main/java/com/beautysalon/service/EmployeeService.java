package com.beautysalon.service;

import com.beautysalon.controller.dto.EmployeeRequest;
import com.beautysalon.controller.dto.EmployeeResponse;
import com.beautysalon.repository.EmployeeRepository;
import com.beautysalon.repository.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public void save(EmployeeRequest employeeRequest) {
        final Employee employee = mapper.map(employeeRequest);
        repository.save(employee);
    }

    public EmployeeResponse findResponseById(Integer id) {
        final Employee employee = repository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found!"));
        return mapper.map(employee);

    }
    public EmployeeResponse findResponseByName(String name) {
        if(!repository.existsByName(name)){
            throw new NullPointerException("Client with this name does not exist!");
        }
        final Employee employee = repository.findByName(name);
        return mapper.map(employee);
    }

    public void deleteBookingById(Integer id) {
        repository.deleteById(id);
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }
}
