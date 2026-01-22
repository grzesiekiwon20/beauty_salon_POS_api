package com.beautysalon.cart;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@SessionAttributes("cart")
@Controller
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;


    @RequestMapping(method = RequestMethod.GET)
    public String createCart(
            @ModelAttribute("cart") List<CartItem> cart,
            Model model,
            Authentication authentication,
            HttpSession session
    ) {
        if (authentication != null) {
            cart = service.mergeCarts(cart, authentication, session);
        }
        model.addAttribute("cart", cart);
        model.addAttribute("total" , service.total(cart));
        return "shopping_cart";

    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public RedirectView addItem(
            @ModelAttribute("cart") List<CartItem> cart,
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") Integer quantity,
            Authentication authentication,
            RedirectAttributes attributes
    ) {
        attributes.addFlashAttribute("cart", service.addItem(cart, productId, quantity, authentication));
        return new RedirectView("/api/cart");
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

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public RedirectView removeItem(
            @ModelAttribute("cart") List<CartItem> cart,
            @RequestParam Long productId,
            Authentication authentication,
            RedirectAttributes redirectAttributes
    ) {
        service.removeItem(cart, productId, authentication);
        redirectAttributes.addFlashAttribute("cart", cart);
        return new RedirectView("/api/cart");
    }

    @GetMapping("/clear")
    public RedirectView clearCart(
            @ModelAttribute("cart") List<CartItem> cart,
            RedirectAttributes redirectAttributes,
            Authentication authentication
    ) {
        if (authentication != null) {
            redirectAttributes.addFlashAttribute("cart", service.clearCart(cart, authentication));
        }
        cart = new ArrayList<>();
        redirectAttributes.addFlashAttribute("cart", cart);
        return new RedirectView("/api/cart");
    }
}
