package com.beautysalon.employee;


import com.beautysalon.employee.dto.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping("/createEmployee")
    public ResponseEntity<Long> createEmployee(
            @RequestParam String email
    ){
        return ResponseEntity.ok(service.saveEmployee(email));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){
        return ResponseEntity.ok(service.findAllEmployees());
    }
}
