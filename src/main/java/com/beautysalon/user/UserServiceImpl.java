package com.beautysalon.user;

import com.beautysalon.exception.EmailAlreadyExistsException;
import com.beautysalon.exception.UsernameAlreadyExistsException;
import com.beautysalon.role.Role;
import com.beautysalon.role.RoleRepository;
import com.beautysalon.user.dto.UserEntityRequest;
import com.beautysalon.user.dto.UserEntityResponse;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper mapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserEntityResponse registerUser(final UserEntityRequest userEntityRequest) {
        if (userEntityRequest == null) {
            throw new IllegalArgumentException("UserRequest cannot be null");
        }

        if (userRepository.existsByUsername(userEntityRequest.username())) {
            throw new UsernameAlreadyExistsException("Username already taken");
        }

        if (userRepository.existsByEmail(userEntityRequest.email())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        final Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Default role 'USER' not found"));

        final UserEntity user = mapper.toEntity(userEntityRequest);

        final String encodedPassword = passwordEncoder.encode(userEntityRequest.password());
        assert encodedPassword != null;
        user.setPassword(encodedPassword);
        Set<Role> roleSet = Collections.singleton(userRole);
        user.setRoles(roleSet);

        final UserEntity savedUser = userRepository.save(user);

        return mapper.mapToUserEntityResponse(savedUser);
    }

    @Override
    @NonNull
    public UserDetails loadUserByUsername(final @NonNull String username) throws UsernameNotFoundException {
        if (!userRepository.existsByUsername(username)) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        final UserEntity user = userRepository.findByUsername(username);

        final List<GrantedAuthority> grantedAuthorities = this.getAuthorities(user);


        return new User(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities
        );
    }

    private List<GrantedAuthority> getAuthorities(final UserEntity user) {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }

}