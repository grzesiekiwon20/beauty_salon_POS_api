package com.beautysalon.customer;


import com.beautysalon.activity.Activity;
import com.beautysalon.cart.Cart;
import com.beautysalon.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Customer extends BaseEntity{

    private String userId;

    @OneToOne(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval = true)
    private Cart cart;

    @OneToMany( mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Activity> activities;

}
