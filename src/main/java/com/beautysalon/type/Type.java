package com.beautysalon.type;


import com.beautysalon.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Type extends BaseEntity {

    private String name;
    private String category;
    private String description;
    private String price;
    private String duration;
    private String activityImage;
}
