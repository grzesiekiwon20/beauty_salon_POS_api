package com.beautysalon.user;


import com.beautysalon.common.BaseEntity;
import com.beautysalon.role.Role;
import com.beautysalon.role.RoleRepository;
import com.beautysalon.user.dto.UserEntityRequest;
import com.beautysalon.user.dto.UserEntityResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class UserMapper {


    public UserEntity toEntity(UserEntityRequest request) {
        return UserEntity.builder()
                .username(request.username())
                .password(request.password())
                .email(request.email())
                .enabled(true)
                .locked(false)
                .addresses(new HashSet<>())
                .roles(new HashSet<>())
                .build();
    }

    public UserEntityResponse mapToUserEntityResponse(UserEntity userEntity) {
        return UserEntityResponse.builder()
                .userId(userEntity.getUserId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .enabled(userEntity.isEnabled())
                .locked(userEntity.isLocked())
                .roles(userEntity.getRoles().stream().map(Role::getId).collect(Collectors.toSet()))
                .addresses(userEntity.getAddresses().stream().map(BaseEntity::getId).collect(Collectors.toSet())).build();
    }
}
