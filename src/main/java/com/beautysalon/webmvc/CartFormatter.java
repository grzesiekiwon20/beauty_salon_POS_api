package com.beautysalon.webmvc;

import com.beautysalon.cart.CartService;
import com.beautysalon.cart.CartResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CartFormatter implements Formatter<CartResponse> {


    @Autowired
    private CartService cartService;

    @Override
    public CartResponse parse(@NonNull String cartId, @NonNull Locale locale) throws ParseException {
        return this.cartService.findCart(cartId);
    }

    @Override
    @NonNull
    public String print(CartResponse object, @NonNull Locale locale) {
        return (object != null ? object.getCartId() : "");
    }
}