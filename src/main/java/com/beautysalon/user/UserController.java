package com.beautysalon.user;


import com.beautysalon.address.AddressServiceImpl;
import com.beautysalon.user.dto.UserEntityResponse;
import com.beautysalon.user.dto.UserEntityRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final AddressServiceImpl addressService;



    @GetMapping(value = "/register")
    public String registerUser(Model model){
        UserEntityRequest userEntityRequest = new UserEntityRequest();
        model.addAttribute("user" , userEntityRequest);
        return "/user/registerForm";
    }
    @PostMapping("/save")
    public String saveUser(
            @ModelAttribute("user") @Valid UserEntityRequest userEntityRequest
    ){
        userServiceImpl.registerUser(userEntityRequest, passwordEncoder);
        return "redirect:/";
    }

    @GetMapping("/user" )
    public String  getUserByUsername( Model model,
            Authentication authentication
    ){
        model.addAttribute("addressDetails", addressService.findUsersHomeAddress(authentication));
        model.addAttribute("userDetails" , userServiceImpl.getLoggedInUserDetails(authentication));
        return "/user/user_details";
    }


    @GetMapping("/byRole/{roleName}")
    public String listOfUsersWithGivenRole(
           @PathVariable final String roleName, Model model){
        ;
        return "employees";
    }

}
