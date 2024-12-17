package com.beautysalon.cart;


import com.beautysalon.cart.dto.CartResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Cart", description = "The Cart Api")
@RestController
@RequestMapping("cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/createCart")
    public ResponseEntity<Long> createCart(
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.createCartForCustomer(connectedUser));
    }

    @GetMapping("/cartById/{cartId}")
    public ResponseEntity<CartResponse> getCartById(
            @PathVariable Long cartId
    ){
        return ResponseEntity.ok(service.findCartResponse(cartId));
    }
    @PutMapping("/addProductToCart/{productId}/quantity")
    public ResponseEntity<CartResponse> addToCart(
        @PathVariable Long productId,
        @RequestParam Integer quantity,
        Authentication authentication
    ){
        return ResponseEntity.ok(service.addProductToCart(productId, quantity,authentication));
    }

}
