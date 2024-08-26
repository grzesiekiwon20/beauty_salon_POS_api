package com.beautysalon.repository.model.users;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Embeddable
public class LoginDetails {

    private String username;
    private String password;

    public LoginDetails() {
    }
}
