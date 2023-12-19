package com.beautysalon.controller;


import com.beautysalon.controller.dto.BookingRequest;
import com.beautysalon.repository.model.ServiceType;
import com.beautysalon.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;


@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;
    //Endpoint to find booking by ID
    @GetMapping("/api/booking/id")
    public ResponseEntity<?> findById(@RequestParam Integer id) {
        try {
            return new ResponseEntity<>(service.find(id), HttpStatus.ACCEPTED);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Endpoint to create booking
    @PostMapping("/api/booking")
    public void createBooking(@RequestBody BookingRequest booking) {
        service.save(booking);
    }
    //Endpoint to find bookings by Date
    @GetMapping("/api/booking/date")
    public ResponseEntity<?> findByDate(@RequestParam LocalDate date) {
        try {
            return new ResponseEntity<>(service.findResponseByDate(date), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    //Endpoint to find bookings by ServiceType
    @GetMapping("/api/booking/serviceType")
    public ResponseEntity<?> findByServiceType(@RequestParam ServiceType serviceType) {
        try {
            return new ResponseEntity<>(service.findResponseByServiceType(serviceType), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/booking/email")
    public ResponseEntity<?> findByEmail(String email){
        try{
            return new ResponseEntity<>(service.findByEmail(email), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/api/booking/startTime")
    public ResponseEntity<?> findBookingsByStartTime(String startTime){
        try{
            return new ResponseEntity<>(service.findByStartTime(startTime), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/api/booking/finishTime")
    public ResponseEntity<?> findBookingsByFinishTime(String finishTime){
        try{
            return new ResponseEntity<>(service.findByFinishTime(finishTime), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/api/booking")
    public ResponseEntity<?> findAll(){
        try{
            return new ResponseEntity<>(service.findAll(), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
