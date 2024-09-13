package com.beautysalon.role;


import com.beautysalon.user.User;
import com.beautysalon.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public Integer saveRole(RoleRequest roleRequest){
        return roleRepository.save(
                Role.builder()
                        .id(roleRequest.id())
                        .name(roleRequest.name())
                        .userList(new ArrayList<>())
                .build()).getId();
    }

    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }

    public void assignRoles(Long userId, Integer roleId){
        User user = userRepository.findById(userId).orElseThrow(()-> new NullPointerException("No User Found"));
        Role role = roleRepository.findById(roleId).orElseThrow(()-> new NullPointerException("No Role Found"));
        List<Role> listToSave = new ArrayList<>(user.getRoleList().stream().toList());
        listToSave.add(role);
        user.setRoleList(listToSave);
        userRepository.save(user);
    }
}
