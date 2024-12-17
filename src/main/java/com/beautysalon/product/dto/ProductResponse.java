package com.beautysalon.product.dto;


import com.beautysalon.category.Category;
import com.beautysalon.product.InventoryStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double discount;
    private Double specialPrice;
    private Integer stockQuantity;
    private InventoryStatus inventoryStatus;
    private byte[] image;
    private Category category;
    private List<Long> products;
    private List<Long> orderItems;

    public ProductResponse(Long id, String name, String description, Double price, Double discount, Double specialPrice, Integer stockQuantity, InventoryStatus inventoryStatus, byte[] image, Category category, List<Long> products, List<Long> orderItems) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.specialPrice = specialPrice;
        this.stockQuantity = stockQuantity;
        this.inventoryStatus = inventoryStatus;
        this.image = image;
        this.category = category;
        this.products = products;
        this.orderItems = orderItems;
    }

    public ProductResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(Double specialPrice) {
        this.specialPrice = specialPrice;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Long> getProducts() {
        return products;
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }

    public List<Long> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Long> orderItems) {
        this.orderItems = orderItems;
    }

    public InventoryStatus getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(InventoryStatus inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }
}
