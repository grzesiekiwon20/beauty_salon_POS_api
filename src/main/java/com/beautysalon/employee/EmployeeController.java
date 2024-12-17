package com.beautysalon.employee;


import com.beautysalon.employee.dto.EmployeeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }


    @PostMapping("/addEmployee/{id}")
    public ResponseEntity<Long> createEmployee(
            @PathVariable String id
    ){
        return ResponseEntity.ok(service.saveEmployee(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){
        return ResponseEntity.ok(service.findAllEmployees());
    }
}
