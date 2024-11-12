package com.beautysalon.cart;


import com.beautysalon.cart.dto.CartResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Cart", description = "The Cart Api")
@RestController
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;


    @PostMapping("/createCart")
    public ResponseEntity<Long> createCart(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.saveCart(connectedUser));
    }

    @GetMapping("/cartByCustomerId/{customerId}")
    public ResponseEntity<CartResponse> getCartById(
            @PathVariable Long customerId
    ){
        return ResponseEntity.ok(service.findCartResponse(customerId));
    }
}
