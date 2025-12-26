package com.beautysalon.user;


import com.beautysalon.user.dto.UserEntityResponse;
import com.beautysalon.user.dto.UserEntityRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@Tag(name = "UserEntity Api")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @PostMapping(value = "/register" , name = "Register user post method")
    public ResponseEntity<UserEntityResponse> registerUser(
            @RequestBody UserEntityRequest userEntityRequest
    ){
        return ResponseEntity.ok(userServiceImpl.registerUser(userEntityRequest));
    }
}
