package com.beautysalon.cartitem;


import com.beautysalon.cartitem.dto.CartItemRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cart Item", description = "The Cart Item Api")
@RestController
@RequestMapping("cartItem")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/createCartItem")
    public ResponseEntity<Long> createCartItem(
            @RequestParam Long cartId,
            @RequestParam Long productId,
            @RequestBody CartItemRequest cartItemRequest
    ){
        return ResponseEntity.ok(cartItemService.saveCart(cartId, productId,cartItemRequest));
    }
}
