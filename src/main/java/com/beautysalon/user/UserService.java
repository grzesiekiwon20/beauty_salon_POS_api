package com.beautysalon.user;

import com.beautysalon.user.dto.UserEntityRequest;
import com.beautysalon.user.dto.UserEntityResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserEntityResponse registerUser(UserEntityRequest userEntityRequest, PasswordEncoder passwordEncoder);

    UserEntityResponse getLoggedInUserDetails(Authentication authentication);

    Boolean existsByUsername(String username);

     List<UserEntityResponse> getUsersByRole(String roleName);
}
