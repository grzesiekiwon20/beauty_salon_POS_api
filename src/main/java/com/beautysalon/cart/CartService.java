package com.beautysalon.cart;

import com.beautysalon.common.BaseEntity;
import com.beautysalon.product.Product;
import com.beautysalon.product.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;


    public List<CartItem> addItem(List<CartItem> cart, Long productId, Integer quantity, Authentication authentication) {
        Optional<Product> product = productRepository.findById(productId);
       if(product.isPresent()){
           CartItem newItem = CartItem.builder()
                   .product(product.get())
                   .unitPrice(product.get().getPrice())
                   .quantity(quantity)
                   .build();

           if (authentication != null) {
               if (cartItemRepository.findCartItemByUsernameAndProductId(authentication.getName(), productId) != null) {
                   CartItem cartItem = cartItemRepository.findCartItemByUsernameAndProductId(authentication.getName(), productId);
                   Integer existingQuantity = cartItem.getQuantity();
                   cartItem.setQuantity(existingQuantity + quantity);
                   cartItemRepository.save(cartItem);
               } else {
                   newItem.setUsername(authentication.getName());
                   cartItemRepository.save(newItem);
               }
           } else {
               if (!cart.isEmpty()) {
                   return mergeItemIntoCartItems(cart, newItem);
               } else {
                   cart.add(newItem);
                   return cart;
               }
           }
       }
        return cart;
    }


    private List<CartItem> mergeItemIntoCartItems(List<CartItem> cartItems, CartItem newItem) {
        int counter = 0;
        for (CartItem cartItem : cartItems) {
            if (Objects.equals(cartItem.getProduct().getId(), newItem.getProduct().getId())) {
                Integer newQuantity = cartItem.getQuantity() + newItem.getQuantity();
                cartItem.setQuantity(newQuantity);
                counter++;
            }
        }
        if (counter == 0) cartItems.add(newItem);

        return cartItems;
    }


    public List<CartItem> clearCart(List<CartItem> cart, Authentication authentication) {
        List<CartItem> existingCart = cartItemRepository.findByUsername(authentication.getName());
        cartItemRepository.deleteAll(existingCart);
        return existingCart;
    }


    public List<CartItem> mergeCarts(List<CartItem> cart, Authentication authentication, HttpSession session) {
        Integer counter = (Integer) session.getAttribute("counter");
        if (counter == null) {
            counter = 0;
        }
        if (counter < 1) {
            for (CartItem item : cart) {
                if (cartItemRepository.existsByProductId(item.getProduct().getId()) == null) {
                    item.setUsername(authentication.getName());
                    cartItemRepository.save(item);
                } else {
                    CartItem existingItem = cartItemRepository.findCartItemByUsernameAndProductId(authentication.getName(), item.getProduct().getId());
                    Integer newQuantity = existingItem.getQuantity() + item.getQuantity();
                    existingItem.setQuantity(newQuantity);
                    cartItemRepository.save(existingItem);
                }
            }
        }
        counter++;
        session.setAttribute("counter", counter);
        return cartItemRepository.findByUsername(authentication.getName());
    }

    public void removeItem(List<CartItem> cartItems, Long productId, Authentication authentication) {
        if (authentication != null) {
            if (cartItemRepository.findCartItemByUsernameAndProductId(authentication.getName(), productId) != null) {
                cartItemRepository.deleteCartItemByUsernameAndProductId(authentication.getName(), productId);
            }
        }
    }

    public void updateCart(Long cartItemId, String up, String down) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new EntityNotFoundException("No cart item found with id: " + cartItemId));

        cartItemRepository.save(cartItem);
    }

    public BigDecimal total(List<CartItem> cart) {
        return cart.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

