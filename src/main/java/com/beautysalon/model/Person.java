package com.beautysalon.model;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Person {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

}
