package com.beautysalon.address;


import com.beautysalon.address.dto.AddressRequest;
import com.beautysalon.common.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressServiceImpl service;

    @GetMapping("/addAddress")
    public String create(
            Model model
    ){
        AddressRequest addressRequest = new AddressRequest();
        model.addAttribute("address", addressRequest);
        return "/address/addressmng";
    }
    @PostMapping("/save")
    public String saveAddress(
            Authentication authentication,
            @ModelAttribute("address") AddressRequest addressRequest
    ){
        service.saveAddress(addressRequest, authentication);
        return "redirect:/address/";
    }
    @GetMapping("/")
    public String getAddressesResponseForConnectedUser(
            Authentication connectedUser, Model model
    ){
        model.addAttribute("addressList" ,service.getAddressResponsesListFromRepositoryForConnectedUser(connectedUser));
        return "/address/address_book";
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

    @GetMapping("/byId/{addressId}")
    public String getAddressResponseById(Model model,
            @PathVariable Long addressId
    ){
        model.addAttribute("address", service.getAddressResponseByAddressId(addressId));
        return "/address/address_details";
    }

    @DeleteMapping("/remove/{addressId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<MessageResponse> removeAddressById(
            @PathVariable Long addressId
    ){
        return ResponseEntity.ok(service.removeAddressByAddressId(addressId));
    }
}
