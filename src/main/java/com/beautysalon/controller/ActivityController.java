package com.beautysalon.controller;


import com.beautysalon.controller.dto.ActivityRequest;

import com.beautysalon.repository.model.ServiceType;
import com.beautysalon.service.impl.GeneralServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@CrossOrigin(origins = "http://localhost:51862")
@RequiredArgsConstructor
@RequestMapping("/api/activities")
public class ActivityController {

    private final GeneralServiceImpl service;


    //Endpoint to find activity by ID

    @PostMapping("/create")
    public void createActivity(@RequestBody ActivityRequest activityRequest){
        service.saveActivity(activityRequest);
    }

    @GetMapping("/listById/id")
    public ResponseEntity<?> findListOfActivitiesByUserId(@RequestParam Long id){
        try{
            return new ResponseEntity<>(service.findActivitiesByUserId(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Endpoint to create activity

//    @GetMapping("/date")
//    public ResponseEntity<?> findByDate(@RequestParam LocalDate date, @RequestParam UserType userType) {
//        try {
//            return new ResponseEntity<>(service.findListOfActivityResponsesByDateAndUserType(date, userType), HttpStatus.ACCEPTED);
//        }catch (Exception e){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//    }
//
//    @GetMapping("/serviceType")
//    public ResponseEntity<?> findByServiceType(@RequestParam ServiceType serviceType, @RequestParam UserType userType) {
//        try {
//            return new ResponseEntity<>(service.findActivityResponsesByServiceTypeAndUserType(serviceType, userType), HttpStatus.ACCEPTED);
//        }catch (Exception e){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/username")
    public ResponseEntity<?> findByUsername(@RequestParam String username){
        try{
            return new ResponseEntity<>(service.findListOfActivityResponsesByUsername(username), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/startTime")
    public ResponseEntity<?> findActivitiesByStartTime(String startTime){
        try{
            return new ResponseEntity<>(service.findListOfActivityResponsesByStartTime(startTime), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/finishTime")
    public ResponseEntity<?> findActivitiesByFinishTime(String finishTime){
        try{
            return new ResponseEntity<>(service.findListOfActivityResponsesByFinishTime(finishTime), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("")
    public ResponseEntity<?> findAll(){
        try{
            return new ResponseEntity<>(service.findAllActivityResponses(), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{activityId}/userIds")
    public ResponseEntity<?> setUsersForActivity(@PathVariable Long activityId, @RequestBody List<Long> userIds){
        try{
            return new ResponseEntity<>(service.assignUsersToActivity(activityId, userIds), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/serviceType")
    public ResponseEntity<?> findActivityByServiceType(@RequestParam ServiceType serviceType){
        try{
            return  new ResponseEntity<>(service.findListOfActivityResponseByServiceType(serviceType), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
