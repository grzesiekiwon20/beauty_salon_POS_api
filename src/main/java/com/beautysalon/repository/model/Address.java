package com.beautysalon.repository.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity(name = "ADDRESS")
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue
    @Column(name = "ADDRESS_ID")
    private Integer addressId;
    private String street;
    private String city;
    private String postCode;
    private AddressType addressType;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Address address = (Address) o;
        return getAddressId() != null && Objects.equals(getAddressId(), address.getAddressId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
