package com.beautysalon.payment;

import com.beautysalon.common.BaseEntity;
import com.beautysalon.order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@Table(name = "payment")
@EntityListeners(AuditingEntityListener.class)
public class Payment extends BaseEntity {


    @OneToOne(mappedBy = "payment", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Order order;

    @NotBlank
    @Size(min = 4, message = "Payment method must contain at least 4 characters")
    private String paymentMethod;

    public Payment(Long id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, String createdBy, String lastModifiedBy, Order order, String paymentMethod) {
        super(id, createdDate, lastModifiedDate, createdBy, lastModifiedBy);
        this.order = order;
        this.paymentMethod = paymentMethod;
    }

    public Payment() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public @NotBlank @Size(min = 4, message = "Payment method must contain at least 4 characters") String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(@NotBlank @Size(min = 4, message = "Payment method must contain at least 4 characters") String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
