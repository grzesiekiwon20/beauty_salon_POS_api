package com.beautysalon.product;


import com.beautysalon.cartitem.CartItem;
import com.beautysalon.category.Category;
import com.beautysalon.common.BaseEntity;
import com.beautysalon.orderItem.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product extends BaseEntity {


@NotNull
    @Size(min = 3, max = 50)
    private String name;
    @NonNull
    @Size(min = 3, max = 100, message = "Name has to be longer than 2 characters and shorter or equal 100")
    private String description;
    @NonNull
    private Double price;
    private Double discount;
    private Double specialPrice;
    @NonNull
    private Integer stockQuantity;
    @NonNull
    private InventoryStatus inventoryStatus;

    @JsonIgnore
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<CartItem> products;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<OrderItem> orderItems;

    public Product(Long id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, String createdBy, String lastModifiedBy, String name, @NonNull String description, @NonNull Double price, Double discount, Double specialPrice, @NonNull Integer stockQuantity, @NonNull InventoryStatus inventoryStatus, String image, Category category, List<CartItem> products, List<OrderItem> orderItems) {
        super(id, createdDate, lastModifiedDate, createdBy, lastModifiedBy);
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

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public Double getPrice() {
        return price;
    }

    public void setPrice(@NonNull Double price) {
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

    @NonNull
    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(@NonNull Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @NonNull
    public InventoryStatus getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(@NonNull InventoryStatus inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<CartItem> getProducts() {
        return products;
    }

    public void setProducts(List<CartItem> products) {
        this.products = products;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
