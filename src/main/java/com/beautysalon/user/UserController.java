package com.beautysalon.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserDetails(
            @Valid @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping("/role")
    public ResponseEntity<List<UserResponse>> getUserByAuthority(
            @RequestParam String role
    ){
        return ResponseEntity.ok(service.findByAuthority(role));
    }

    @GetMapping("/account-details")
    public ResponseEntity<UserResponse> getDetailsOfConnectedUser(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findByUser(connectedUser));
    }
    @GetMapping("/address-list")
    public ResponseEntity<List<Long>> getAddressIdsListFromConnectedUser(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAddressIdsList(connectedUser));
    }
}
