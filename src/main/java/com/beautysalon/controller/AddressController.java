package com.beautysalon.controller;


import com.beautysalon.controller.dto.AddressRequest;
import com.beautysalon.service.impl.GeneralServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Address", description = "The Address Api")
@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

    private final GeneralServiceImpl service;

    @PostMapping("/create")
    public void createAddress(@RequestBody AddressRequest request){
        service.saveAddress(request);
    }

    @PatchMapping("/patch/{id}/userIds")
    public ResponseEntity<?> assignIdsForAddress(@PathVariable Long id, @RequestBody List<Long> userIds){
        try {
            return new ResponseEntity<>(service.addressAssignment(id,userIds), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
