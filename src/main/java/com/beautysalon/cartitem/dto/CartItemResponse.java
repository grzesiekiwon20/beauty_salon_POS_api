package com.beautysalon.cartitem.dto;



public class CartItemResponse {

    private Long id;
    private Long cartId;
    private byte[] image;
    private String name;
    private Integer quantity;
    private double subTotal;
    private double discount;
    private double productPrice;

    public CartItemResponse(Long id, Long cartId, byte[] image, String name, Integer quantity, double subTotal, double discount, double productPrice) {
    this.id  =id;
        this.cartId = cartId;
        this.image = image;
        this.name = name;
        this.quantity = quantity;
        this.subTotal = subTotal;
        this.discount = discount;
        this.productPrice = productPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartId;
    }


    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public CartItemResponse() {

    }
}
