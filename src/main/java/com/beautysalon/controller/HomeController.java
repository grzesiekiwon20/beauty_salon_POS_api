package com.beautysalon.controller;


import com.beautysalon.controller.dto.UserResponse;
import com.beautysalon.repository.UserRepository;
import com.beautysalon.repository.model.users.User;
import com.beautysalon.repository.model.users.UserDetailsInfo;
import com.beautysalon.service.impl.GeneralServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Base64;

@RestController
@RequestMapping("/api/home")
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public HomeController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public boolean login(@RequestBody UserDetailsInfo userDetailsInfo){
        User user = userRepository.findByEmail(userDetailsInfo.getUsername());

        return userDetailsInfo.getUsername().equals(user.getEmail()) && passwordEncoder.encode(userDetailsInfo.getPassword()).equals(user.getPassword());
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }
}
