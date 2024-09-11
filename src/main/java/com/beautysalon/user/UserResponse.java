package com.beautysalon.user;


import com.beautysalon.activity.Activity;
import com.beautysalon.address.Address;
import com.beautysalon.role.Role;
import com.jayway.jsonpath.internal.function.sequence.First;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<Integer> roles;
    private List<Long> addresses;

}
