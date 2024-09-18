package com.beautysalon.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<Integer> roles;
    private List<Long> addresses;
    private List<Long> activities;

}
