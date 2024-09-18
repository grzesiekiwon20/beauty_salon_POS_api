package com.beautysalon.user;


import com.beautysalon.address.Address;
import com.beautysalon.common.BaseEntity;
import com.beautysalon.role.Role;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponse map(User user){
        return UserResponse
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .roles(user.getRoleList().stream().map(Role::getId).collect(Collectors.toList()))
                .addresses(user.getAddresses().stream().map(BaseEntity::getId).collect(Collectors.toList()))
                .activities(user.getActivities().stream().map(BaseEntity::getId).collect(Collectors.toList()))
                .build();
    }
}
