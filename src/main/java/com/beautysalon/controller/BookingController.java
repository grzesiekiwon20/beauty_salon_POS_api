package com.beautysalon.controller;


import com.beautysalon.controller.dto.BookingRequest;
import com.beautysalon.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @GetMapping("/api/booking")
    public ResponseEntity<?> findById(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(service.findResponseById(id), HttpStatus.ACCEPTED);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/api/booking")
    public void createBooking(@RequestBody BookingRequest booking) {
        service.save(booking);
    }
    @GetMapping("/api/booking/date")
    public ResponseEntity<?> findByDate(@RequestParam String date) {
        try {
            return new ResponseEntity<>(service.findResponseByDate(date), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @GetMapping("/api/booking/startTime")
    public ResponseEntity<?> findByStartTime(@RequestParam String startTime) {
        try {
            return new ResponseEntity<>(service.findResponseByStartTime(startTime), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/booking/endTime")
    public ResponseEntity<?> findByEndTime(@RequestParam String endTime) {
       try {
              return new ResponseEntity<>(service.findResponseByEndTime(endTime), HttpStatus.ACCEPTED);
       }catch (Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @GetMapping("/api/booking/serviceType")
    public ResponseEntity<?> findByServiceType(@RequestParam String serviceType) {
        try {
            return new ResponseEntity<>(service.findResponseByServiceType(serviceType), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
