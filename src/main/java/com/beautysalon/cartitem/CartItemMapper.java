package com.beautysalon.cartitem;


import com.beautysalon.cartitem.dto.CartItemResponse;
import com.beautysalon.file.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {



    public CartItemResponse cartItemResponse (CartItem cartItem){
        CartItemResponse cartItemResponse = new CartItemResponse();
        cartItemResponse.setId(cartItem.getId());
        cartItemResponse.setCartId(cartItem.getCart().getId());
        cartItemResponse.setName(cartItem.getProduct().getName());
        cartItemResponse.setSubTotal(cartItem.getSubTotal());
        cartItemResponse.setQuantity(cartItem.getQuantity());
        cartItemResponse.setDiscount(cartItem.getProduct().getDiscount());
        cartItemResponse.setProductPrice(cartItem.getProduct().getPrice());
        cartItemResponse.setImage(FileUtils.readFileFromLocation(cartItem.getProduct().getImage()));
        return cartItemResponse;
    }
}
