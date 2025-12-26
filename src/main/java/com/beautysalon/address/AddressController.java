package com.beautysalon.address;


import com.beautysalon.address.dto.AddressRequest;
import com.beautysalon.address.dto.AddressResponse;
import com.beautysalon.common.MessageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Address", description = "The Address Api")
@RestController
@RequestMapping("address")
public class AddressController {

    private final AddressServiceImpl service;

    public AddressController(AddressServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/addAddress")
    public ResponseEntity<MessageResponse> addAddress(
            @Valid @RequestBody AddressRequest request,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.saveAddress(request, connectedUser));
    }

    @GetMapping("/username")
    public ResponseEntity<List<AddressResponse>> getAddressesResponseForConnectedUser(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.getAddressResponsesListFromRepositoryForConnectedUser(connectedUser));
    }

    @PutMapping("/update-address/{addressId}")
    public ResponseEntity<MessageResponse> updateAddress(
            @PathVariable Long addressId,
            @RequestParam(required = false) String firstLineAddress,
            @RequestParam (required = false) String secondLineAddress,
            @RequestParam (required = false) String city,
            @RequestParam (required = false) String postCode
    ){
         return ResponseEntity.ok(service.updateExistingAddress(addressId, firstLineAddress,secondLineAddress,city,postCode));
    }
    @GetMapping("/private/{addressId}")
    public ResponseEntity<AddressResponse> getAddressResponseById(
            @PathVariable Long addressId
    ){
        return ResponseEntity.ok(service.findAddressResponseById(addressId));
    }

    @DeleteMapping("/remove/{addressId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<MessageResponse> removeAddressById(
            @PathVariable Long addressId
    ){
        return ResponseEntity.ok(service.removeAddressByAddressId(addressId));
    }
}
