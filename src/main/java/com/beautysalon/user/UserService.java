package com.beautysalon.user;


import com.beautysalon.activity.Activity;
import com.beautysalon.address.Address;
import com.beautysalon.common.BaseEntity;
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
    private final UserMapper mapper;

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NullPointerException("No User Found"));
        return mapper.map(user);
    }

    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(mapper::map).collect(Collectors.toList());
    }

//    public List<UserResponse> findByRole(String role) {
//        List<User> userList = userRepository.findAll();
//        List<UserResponse> result = new ArrayList<>();
////        userList.forEach(user -> user.getRoleList().forEach(a-> {
////                if(a.getName().equals(role)){
////                    result.add(mapper.map(user));
////                }
////                }));
//        return result;
//    }

    public UserResponse findByUser(Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        User savedUser = userRepository.findById(user.getId()).orElseThrow(()->new NullPointerException("No User Found"));
        return mapper.map(savedUser);
    }

    public List<Long> findAddressIdsList(Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        User existingUser = userRepository.findById(user.getId()).orElseThrow(()->new NullPointerException("No User Found"));
        return existingUser.getAddresses().stream().map(Address::getId).toList();
    }
}
