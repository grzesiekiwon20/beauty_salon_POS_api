package com.beautysalon.cartitem;


import com.beautysalon.cartitem.dto.CartItemResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CartItem", description = "The CartItem Api")
@RequestMapping("cartItem")
@RestController
public class CartItemController {


    private final CartItemService service;

    public CartItemController(CartItemService service) {
        this.service = service;
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartItemResponse>> getCartItemsForCart(
            @PathVariable Long cartId
    ){
        return ResponseEntity.ok(service.findCartItemsForCart(cartId));
    }
    @PutMapping("/updateQuantity/{cartItemId}")
    public ResponseEntity<Long> updateQuantity(
            @PathVariable Long cartItemId,
            @RequestParam Integer quantity
    ){
        return ResponseEntity.ok(service.updateQuantity(cartItemId, quantity));
    }
    @PutMapping("/increaseByOneQuantity/{cartItemId}")
    public ResponseEntity<Long> incrementByOne(
            @PathVariable Long cartItemId
    ){
        return ResponseEntity.ok(service.incrementByOne(cartItemId));
    }
    @PutMapping("/decreaseByOneQuantity/{cartItemId}")
    public ResponseEntity<Long> decrementByOne(
            @PathVariable Long cartItemId
    ){
        return ResponseEntity.ok(service.decrementByOne(cartItemId));
    }
}
