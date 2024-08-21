package com.beautysalon.repository.model.users;


import com.beautysalon.repository.model.Activity;
import com.beautysalon.repository.model.Address;
import com.beautysalon.repository.model.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @Embedded
    private LoginDetails loginDetails;

    private boolean enabled;

    @NonNull
    @Column(name = "EMAIL")
    private String email;

    @NonNull
    @Column(name = "user_type")
    private UserType userType;

    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private List<Activity> activities;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Address> addresses;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(email, user.email) && userType == user.userType && Objects.equals(activities, user.activities) && Objects.equals(addresses, user.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phoneNumber, email, userType, activities, addresses);
    }
}
