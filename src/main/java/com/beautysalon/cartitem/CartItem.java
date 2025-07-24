package com.beautysalon.cartitem;


import com.beautysalon.cart.Cart;
import com.beautysalon.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JsonIgnore
    private Product product;

    @Min(1)
    private Integer quantity;

    private double subTotal;


    public CartItem() {
    }

    public CartItem(Long id, Cart cart, Product product, Integer quantity, double subTotal) {
        this.id = id;
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
