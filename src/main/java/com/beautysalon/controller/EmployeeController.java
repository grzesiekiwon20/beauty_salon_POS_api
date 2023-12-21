package com.beautysalon.controller;

import com.beautysalon.controller.dto.EmployeeRequest;
import com.beautysalon.service.EmployeeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping("/api/employee/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveEmployee(@RequestBody EmployeeRequest employee){
        service.save(employee);
    }

    @GetMapping("/api/employee/id")
    public ResponseEntity<?> findEmployeeById(@RequestParam Integer id){
        try{
            return new ResponseEntity<>(service.findResponseById(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/api/employee/name")
    public ResponseEntity<?> findEmployeeByName(@RequestParam String name){
        try{
            return new ResponseEntity<>(service.findResponseByName(name),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/api/employee/delete")
    public void deleteById(Integer id){
        service.deleteBookingById(id);
    }
    @GetMapping("/api/employee")
    public ResponseEntity<?> findAllEmployees(){
        try{
            return new ResponseEntity<>(service.findAll(), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
