package com.beautysalon.controller;

import com.beautysalon.controller.dto.ClientRequest;
import com.beautysalon.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @GetMapping("/api/user/email")
    public ResponseEntity<?> findByEmail(@RequestParam String email) {
        try {
            return new ResponseEntity<>(service.findResponseByEmail(email), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/user/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ClientRequest client) {
        service.save(client);
    }

    @GetMapping("/api/user/id")
    public ResponseEntity<?> findById(@RequestParam Integer id) {
        try {
            return new ResponseEntity<>(service.findResponseById(id), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/user")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteById(@RequestParam Integer id) {
        service.deleteById(id);
    }

    @GetMapping("/api/user/name")
    public ResponseEntity<?> findByName(@RequestParam String name) {
        try {
            return new ResponseEntity<>(service.findResponseByName(name), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
