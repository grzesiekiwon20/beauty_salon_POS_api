package com.beautysalon.category;


import jakarta.persistence.*;
import lombok.*;



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
    private String name;
    private SubCategory subCategory;

}
