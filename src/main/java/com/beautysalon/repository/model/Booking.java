package com.beautysalon.repository.model;

/*
 * Booking Class
 * Represents a booking for beauty salon services.
 *
 * - @Entity: Marks the class as a JPA entity.
 * - @Table: Specifies the table name in the database.
 * - @ToString, @Getter, @Setter, @Builder: Lombok annotations for generating boilerplate code.
 * - @NoArgsConstructor, @AllArgsConstructor: Lombok annotations for generating constructors.
 * - @Id: Marks the id field as the primary key.
 * - @GeneratedValue: Specifies that the id should be generated automatically.
 * - @Column: Specifies the column names in the database.
 *
 * Custom equals and hashCode methods are implemented to properly compare Booking instances.
 * The constructor allows initializing date and service type when creating a booking.
 *
 * Usage example:
 * Booking booking = Booking.builder().date("2023-12-15").serviceType("Haircut").build();
 */

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;


@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue
    @Column(name = "BOOKING_ID")
    private Integer id;
    @Column(name = "CLIENT_EMAIL")
    private String clientEmail;
    @Column(name = "DATE")
    private LocalDate date;
    @Column(name = "SERVICE_TYPE")
    private ServiceType serviceType;
    @Column(name = "USER_ID")
    private Integer clientId;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Booking booking = (Booking) o;
        return getId() != null && Objects.equals(getId(), booking.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "clientEmail = " + clientEmail + ", " +
                "date = " + date + ", " +
                "serviceType = " + serviceType + ")";
    }
}