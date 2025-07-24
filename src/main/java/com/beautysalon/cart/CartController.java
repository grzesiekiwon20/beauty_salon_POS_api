package com.beautysalon.cart;


import com.beautysalon.cart.dto.CartResponse;
import com.beautysalon.cartitem.CartItem;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Cart", description = "The Cart Api")
@RestController
@RequestMapping("carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PutMapping("/addProductToCart/{productId}/quantity")
    public ResponseEntity<CartResponse> updateCart(
            @PathVariable Long productId,
            @RequestParam Integer quantity,
            @RequestParam String sessionId
    ) {
        return ResponseEntity.ok(cartService.updateCart(productId, quantity, sessionId));
    }

    @GetMapping("/bySessionId/{sessionId}")
    public ResponseEntity<CartResponse> getCart(
            @PathVariable String sessionId
    ) {
        return ResponseEntity.ok(cartService.getCartBySessionId(sessionId));
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createCart(
            @RequestParam String sessionId
    ) {
        return ResponseEntity.ok(cartService.createCart(sessionId));
    }


    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
