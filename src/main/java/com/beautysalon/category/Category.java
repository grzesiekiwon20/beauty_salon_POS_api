package com.beautysalon.category;


import com.beautysalon.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;


@Entity
@Table(name = "categories")
public class Category extends BaseEntity {


    @NotBlank
    @Size(min = 5, max = 35, message = "Name can not be shorter than 3 and longer than 35 characters")
    private String name;
    private String description;
    @NonNull
    private SubCategory subCategory;

    public Category(Long id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, String createdBy, String lastModifiedBy, String name, String description, @NonNull SubCategory subCategory) {
        super(id, createdDate, lastModifiedDate, createdBy, lastModifiedBy);
        this.name = name;
        this.description = description;
        this.subCategory = subCategory;

    }

    public Category() {
    }

    public @NotBlank @Size(min = 5, max = 35, message = "Name can not be shorter than 3 and longer than 25 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(min = 5, max = 35, message = "Name can not be shorter than 3 and longer than 25 characters") String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(@NonNull SubCategory subCategory) {
        this.subCategory = subCategory;
    }
}
