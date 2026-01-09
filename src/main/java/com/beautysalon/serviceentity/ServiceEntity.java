package com.beautysalon.serviceentity;


import com.beautysalon.category.Category;
import com.beautysalon.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceEntity extends BaseEntity {

    private String name;

    @ManyToOne
    @JsonIgnore
    private Category category;
    private String description;
    private Double price;
    private LocalTime duration;
    private String image;

}
