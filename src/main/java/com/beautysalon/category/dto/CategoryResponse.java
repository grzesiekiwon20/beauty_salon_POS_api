package com.beautysalon.category.dto;


import com.beautysalon.category.SubCategory;

public class CategoryResponse {

    private Long categoryId;
    private String name;
    private String description;
    private SubCategory subCategory;

    public CategoryResponse(Long categoryId, String name, String description, SubCategory subCategory) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.subCategory = subCategory;
    }

    public CategoryResponse() {
    }
    public Long getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }
}
