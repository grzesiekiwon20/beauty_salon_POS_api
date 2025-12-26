package com.beautysalon.user;

import com.beautysalon.user.dto.UserEntityRequest;
import com.beautysalon.user.dto.UserEntityResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserEntityResponse registerUser(UserEntityRequest userEntityRequest);
}
