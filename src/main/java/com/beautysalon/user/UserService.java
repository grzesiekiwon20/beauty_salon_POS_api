package com.beautysalon.user;


import com.beautysalon.address.Address;
import com.beautysalon.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NullPointerException("No User Found"));

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .roles(user.getRoleList().stream().map(Role::getId).collect(Collectors.toList()))
                .addresses(user.getAddresses().stream().map(Address::getId).collect(Collectors.toList()))
                .build();
    }

    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .roles(user.getRoleList().stream().map(Role::getId).collect(Collectors.toList()))
                .addresses(user.getAddresses().stream().map(Address::getId).collect(Collectors.toList()))
                .build()).toList();
    }

    public List<UserResponse> findByAuthority(String role) {
        List<User> userList = userRepository.findAll();
        List<UserResponse> result = new ArrayList<>();
        userList.forEach(user -> user.getRoleList().forEach(a-> {
                if(a.getName().equals(role)){
                    result.add(
                            UserResponse.builder()
                                    .id(user.getId())
                                    .firstName(user.getFirstName())
                                    .lastName(user.getLastName())
                                    .phoneNumber(user.getPhoneNumber())
                                    .build()
                    );
                }
                }));
        return result;
    }

    public UserResponse findByUser(Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        User savedUser = userRepository.findById(user.getId()).orElseThrow(()->new NullPointerException("No User Found"));
        return UserResponse.builder()
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .phoneNumber(savedUser.getPhoneNumber())
                .build();
    }
}
