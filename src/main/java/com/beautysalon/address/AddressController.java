package com.beautysalon.address;


import com.beautysalon.address.dto.AddressRequest;
import com.beautysalon.address.dto.AddressResponse;
import com.beautysalon.user.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Address", description = "The Address Api")
@RestController
@RequestMapping("address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;
    @PostMapping("/create")
    public ResponseEntity<Long> createAddress(
            @RequestBody AddressRequest request,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.saveAddress(request, connectedUser));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AddressResponse>> getAllAddressResponse(){
        return ResponseEntity.ok(service.getAllAddresses());
    }

    @GetMapping("/userId")
    public ResponseEntity<List<AddressResponse>> getAddressesResponseForConnectedUser(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.getAddressById(connectedUser));
    }
    @PutMapping("/update-address/{addressId}")
    public ResponseEntity<Long> updateAddress(
            @PathVariable Long addressId,
            @RequestBody AddressRequest addressRequest
    ){
         return ResponseEntity.ok(service.updateExistingAddress(addressId, addressRequest));
    }
    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResponse> getAddressResponseById(
            @PathVariable Long addressId
    ){
        return ResponseEntity.ok(service.findAddressResponseById(addressId));
    }

}
