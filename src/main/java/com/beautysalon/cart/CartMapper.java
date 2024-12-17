package com.beautysalon.cart;


import com.beautysalon.cart.dto.CartResponse;
import com.beautysalon.cartitem.CartItemMapper;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    private final CartItemMapper cartItemMapper;

    public CartMapper(CartItemMapper cartItemMapper) {
        this.cartItemMapper = cartItemMapper;
    }

    public CartResponse mapCart(Cart cart){
        CartResponse cartResponse = new CartResponse();
        cartResponse.setCartItems(cart.getCartItems().stream().map(cartItemMapper::cartItemResponse).toList());
        cartResponse.setTotalPrice(cart.getTotalPrice());
        return cartResponse;
    }
}
