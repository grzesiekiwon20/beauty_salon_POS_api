package com.beautysalon.cart;


import java.math.BigDecimal;
import java.util.List;

public interface ICartService {
    public List<Cart> getAllCartItems(String userSessionId);

    public Cart saveItem(Cart cart);

    public boolean deleteItem(Cart cart);
}
