package com.beautysalon.controller;


import com.beautysalon.controller.dto.ActivityRequest;
import com.beautysalon.repository.model.ServiceType;
import com.beautysalon.repository.model.UserType;
import com.beautysalon.service.impl.GeneralServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
public class ActivityController {

    private final GeneralServiceImpl service;
    //Endpoint to find activity by ID
    @GetMapping("/api/activity/id")
    public ResponseEntity<?> findById(@RequestParam Integer id) {
        try {
            return new ResponseEntity<>(service.findActivityById(id), HttpStatus.ACCEPTED);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Endpoint to create activity
    @PostMapping("/api/activity/create")
    public void createBooking(@RequestBody ActivityRequest activityRequest) {
        service.save(activityRequest);
    }

    @GetMapping("/api/activity/date")
    public ResponseEntity<?> findByDate(@RequestParam LocalDate date, @RequestParam UserType userType) {
        try {
            return new ResponseEntity<>(service.findListOfActivityResponsesByDateAndUserType(date, userType), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @GetMapping("/api/activity/serviceType")
    public ResponseEntity<?> findByServiceType(@RequestParam ServiceType serviceType, @RequestParam UserType userType) {
        try {
            return new ResponseEntity<>(service.findActivityResponsesByServiceTypeAndUserType(serviceType, userType), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/activity/email")
    public ResponseEntity<?> findByEmail(String email){
        try{
            return new ResponseEntity<>(service.findListOfActivityResponsesByEmail(email), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/api/activity/startTime")
    public ResponseEntity<?> findActivitiesByStartTime(String startTime){
        try{
            return new ResponseEntity<>(service.findListOfActivityResponsesByStartTime(startTime), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/api/activity/finishTime")
    public ResponseEntity<?> findActivitiesByFinishTime(String finishTime){
        try{
            return new ResponseEntity<>(service.findListOfActivityResponsesByFinishTime(finishTime), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/api/activity")
    public ResponseEntity<?> findAll(){
        try{
            return new ResponseEntity<>(service.findAllActivities(), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
