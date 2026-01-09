package com.beautysalon.order;


import com.beautysalon.common.BaseEntity;
import com.beautysalon.orderItem.OrderItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customer_order")
@EntityListeners(AuditingEntityListener.class)
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {

    @Email
    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<OrderItem> orderItems;

    private LocalDate orderDate;

//    @OneToOne
//    @JoinColumn(name = "payment_id")
//    private Payment payment;

    private Double totalAmount;
    private OrderStatus orderStatus;

}
