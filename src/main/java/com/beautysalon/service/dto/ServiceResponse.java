package com.beautysalon.service.dto;



import java.time.LocalTime;

public class ServiceResponse {

    private Long id;
    private String name;
    private String categoryName;
    private Long categoryId;
    private String description;
    private Double price;
    private LocalTime duration;
    private byte[] image;

    public ServiceResponse(Long id, String name, String categoryName, Long categoryId, String description, Double price, LocalTime duration, byte[] image) {
        this.id = id;
        this.name = name;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.image = image;
    }

    public ServiceResponse() {
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
