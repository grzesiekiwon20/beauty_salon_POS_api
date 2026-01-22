package com.beautysalon.category;


import com.beautysalon.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;


@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private SubCategory subCategory;

}
