package com.beautysalon.category;


import com.beautysalon.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity
@AllArgsConstructor
@Setter
@Getter
@Table(name = "categories")
@SuperBuilder
@NoArgsConstructor
public class Category extends BaseEntity {


    @NotBlank
    @Size(min = 5, max = 15, message = "Name can not be shorter than 3 and longer than 15 characters")
    private String name;
    private String description;
}
