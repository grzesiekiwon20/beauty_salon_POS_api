package com.beautysalon.user;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {


    private final UserService userService;


    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getUserList());
    }

//    @GetMapping("http://localhost:9090/realms/salon-app/protocol/openid-connect/userinfo")
//    public ResponseEntity<?> getUserInfo(){
//        return new ResponseEntity<>()
//    }

}
