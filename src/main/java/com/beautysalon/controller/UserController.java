package com.beautysalon.controller;

import com.beautysalon.controller.dto.UserRequest;
import com.beautysalon.repository.model.UserType;
import com.beautysalon.service.impl.GeneralServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final GeneralServiceImpl service;

    @GetMapping("/api/user/email")
    public ResponseEntity<?> findUserByEmail(@RequestParam String userEmail) {
        try {
            return new ResponseEntity<>(service.findUserResponseByEmail(userEmail), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/user/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody UserRequest user) {
        service.save(user);
    }

    @GetMapping("/api/user/id")
    public ResponseEntity<?> findUserById(@RequestParam Integer id) {
        try {
            return new ResponseEntity<>(service.findUserResponseById(id), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/user/delete/id")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteById(@RequestParam Integer userId) {
        service.deleteUserById(userId);
    }

    @GetMapping("/api/user/name")
    public ResponseEntity<?> findByName(@RequestParam String name) {
        try {
            return new ResponseEntity<>(service.findUserResponseByName(name), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/user")
    public ResponseEntity<?> findAllUserResponses(){
        try{
            return new ResponseEntity<>(service.findListOfUserResponses(), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/user/usertype")
    public ResponseEntity<?> findUsersByUserType(@RequestParam UserType userType){
        try{
            return new ResponseEntity<>(service.findListOfUserResponsesByUserType(userType) , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
