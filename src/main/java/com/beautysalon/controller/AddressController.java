package com.beautysalon.controller;


import com.beautysalon.controller.dto.AddressRequest;
import com.beautysalon.service.impl.GeneralServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Address", description = "The Address Api")
@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

    private final GeneralServiceImpl service;

    @PostMapping("/create")
    public void saveAddress(@RequestBody AddressRequest request){
        service.save(request);
    }
}
