package com.beautysalon.role;

import com.beautysalon.user.User;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

import java.util.List;

public record RoleRequest(
        Integer id,

        @NonNull
        String name,
        List<User> users
) {
}
