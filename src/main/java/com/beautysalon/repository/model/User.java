package com.beautysalon.repository.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @NonNull
    @Column(name = "NAME")
    private String name;

    @NonNull
    @Column(name = "EMAIL")
    private String email;

    @NonNull
    @Column(name = "USER_TYPE")
    private UserType userType;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    protected List<Activity> activities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    protected List<Address> addresses;

    public User(Long id, @NonNull String name, @NonNull String email, UserType userType, List<Activity> activities, List<Address> addresses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.activities = activities;
        this.addresses = addresses;
    }

}
