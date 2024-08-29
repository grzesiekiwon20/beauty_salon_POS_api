package com.beautysalon.controller;

import com.beautysalon.controller.dto.UserRequest;
import com.beautysalon.controller.dto.UserResponse;
import com.beautysalon.repository.model.users.User;
import com.beautysalon.repository.model.users.UserDetailsInfo;
import com.beautysalon.service.impl.GeneralServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Base64;

@Tag(name = "User", description = "The User Api")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("")
public class UserController {

    private final GeneralServiceImpl service;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/api/users/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveClient(@RequestBody UserRequest userRequest) {
        LOG.debug("{}",userRequest);
        service.saveUser(userRequest);
    }


    @DeleteMapping("/delete/id")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteById(@RequestParam Long userId) {
        service.deleteUserById(userId);
    }


    @GetMapping("/api/users")
    public ResponseEntity<?> findAllUserResponses(@AuthenticationPrincipal UserDetailsInfo userDetailsInfo) {
        try {
            return new ResponseEntity<>(service.findListOfUserResponses(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
