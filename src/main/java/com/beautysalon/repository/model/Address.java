package com.beautysalon.repository.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "addresses")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long id;

    @Column(name = "STREET")
    private String street;

    @Column(name = "CITY")
    private String city;

    @Column(name = "POST_CODE")
    private String postCode;

    @Column(name = "ADDRESS_TYPE")
    private AddressType addressType;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "address_user",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) && Objects.equals(street, address.street) && Objects.equals(city, address.city) && Objects.equals(postCode, address.postCode) && addressType == address.addressType && Objects.equals(users, address.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, city, postCode, addressType, users);
    }
}
