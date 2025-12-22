package com.beautysalon.category;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.lang.NonNull;



@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    @Size(min = 5, max = 35, message = "Name can not be shorter than 3 and longer than 35 characters")
    private String name;
    @NonNull
    private SubCategory subCategory;


}
