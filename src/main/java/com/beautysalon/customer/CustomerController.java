package com.beautysalon.customer;


import com.beautysalon.customer.dto.CustomerResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Customer", description = "The Customer Api")
@RestController
@RequestMapping("customers")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CustomerResponse>> getCustomersList(){
        return ResponseEntity.ok(customerService.findCustomers());
    }

    @PostMapping("/create")
    public ResponseEntity<Long> saveCustomer(Authentication authentication){
        return ResponseEntity.ok(customerService.saveUserIntoRepository(authentication.getName()));
    }
    @GetMapping("/checkIfCustomer")
    public ResponseEntity<Boolean> checkIfCustomer(Authentication authentication){
        return ResponseEntity.ok(customerService.checkIfCustomerExist(authentication.getName()));
    }
    @GetMapping("/connectedUser")
    public ResponseEntity<CustomerResponse> getCustomerDetails(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(customerService.getUserDetailsForLoggedInUser(connectedUser));
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerInfo(
            @PathVariable Long customerId
    ){
        return  ResponseEntity.ok(customerService.getUserByIdFromCustomerRepository(customerId));
    }

}
