package com.beautysalon.config;


import com.beautysalon.repository.UserRepository;
import com.beautysalon.repository.model.users.User;
import com.beautysalon.repository.model.users.UserDetailsInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByLoginDetails_Username(username);
        return new UserDetailsInfo(user);
    }
}
