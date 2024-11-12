package com.beautysalon.user;


import com.beautysalon.config.KeycloakAdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


    private final KeycloakAdminService keycloakAdminService;

    public List<User> getUserList(){
        List<User> userList = new ArrayList<>();
        String users = keycloakAdminService.getUsers();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }
}
