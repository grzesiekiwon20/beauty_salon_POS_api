package com.beautysalon.cart;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;


@Controller
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;


    @GetMapping("/getCart")
    public String createCart(
            Model model, Authentication authentication, HttpSession session
    ) {
        Set<CartItem> cartItems = service.getCart(authentication, session).getCartItemSet();
        model.addAttribute("cart", service.getCart(authentication, session));
        model.addAttribute("cartItems", cartItems);
        return "shopping_cart";
    }

    @PostMapping("/addItem")
    public String addItem(
            Model model,
            Authentication authentication,
            HttpSession session,
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") Integer quantity,
            @RequestParam(required = false) Long categoryId
    ) {
        Set<CartItem> set = service.addItemToCart(authentication, session, productId, quantity).getCartItemSet();
        model.addAttribute("cart", service.getCart(authentication, session));
        model.addAttribute("cartItems", set);
        model.addAttribute("categoryId", categoryId);
        if (categoryId == null) {
            return "redirect:/products/all";
        } else {
            return "redirect:/products/buCategory/{categoryId}";
        }

    }

    @PostMapping("/update")
    public String updateCart(
            @RequestParam Long cartItemId,
            @RequestParam(required = false) String up,
            @RequestParam(required = false) String down
    ) {
        service.updateCart(cartItemId, up, down);
        return "redirect:/cart/getCart";
    }

    @GetMapping("/clear")
    public String clearCart(
            Authentication authentication, HttpSession session
    ) {
        service.clearCart(authentication, session);
        return "redirect:/cart/getCart";
    }
}
