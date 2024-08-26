package com.beautysalon.repository.model.users;


import com.beautysalon.repository.model.Activity;
import com.beautysalon.repository.model.Address;
import com.beautysalon.repository.model.UserType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Getter
    @Column(name = "enabled")
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

    @JsonCreator
    public User(@JsonProperty("id") Long id, @NonNull String firstName, @NonNull String lastName, @NonNull String phoneNumber, LoginDetails loginDetails, boolean enabled, @NonNull String email, @NonNull UserType userType, List<Activity> activities, List<Address> addresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.loginDetails = loginDetails;
        this.enabled = enabled;
        this.email = email;
        this.userType = userType;
        this.activities = activities;
        this.addresses = addresses;
    }

    public User(Long id, String firstName, String lastName, String email, LoginDetails loginDetails, UserType userType, List<Activity> activities, List<Address> addresses, boolean enabled) {
    }

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
