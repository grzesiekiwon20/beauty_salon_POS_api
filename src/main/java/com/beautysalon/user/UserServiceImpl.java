package com.beautysalon.user;

import com.beautysalon.address.Address;
import com.beautysalon.address.AddressMapper;
import com.beautysalon.address.AddressRepository;
import com.beautysalon.address.AddressType;
import com.beautysalon.address.dto.AddressRequest;
import com.beautysalon.exception.EmailAlreadyExistsException;
import com.beautysalon.exception.IncorrectPasswordException;
import com.beautysalon.exception.UsernameAlreadyExistsException;
import com.beautysalon.role.Role;
import com.beautysalon.role.RoleRepository;
import com.beautysalon.user.dto.UserEntityRequest;
import com.beautysalon.user.dto.UserEntityResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final AddressMapper addressMapper;
    private final RoleRepository roleRepository;
    private final AddressRepository addressRepository;


    @Override
    public UserEntityResponse registerUser(final UserEntityRequest userEntityRequest, PasswordEncoder passwordEncoder) {
        if (userEntityRequest == null) {
            throw new IllegalArgumentException("UserRequest cannot be null");
        }
        if (!Objects.equals(userEntityRequest.getPassword(), userEntityRequest.getConfirm())) {
            throw new IncorrectPasswordException("Passwords not match. Try again");
        }
        if (userRepository.existsByUsername(userEntityRequest.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already taken");
        }

        if (userRepository.existsByEmail(userEntityRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }
        final Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Default role 'USER' not found"));
        final UserEntity user = mapper.toEntity(userEntityRequest, passwordEncoder);
        final AddressRequest addressRequest = AddressRequest.builder()
                .street(userEntityRequest.getStreet())
                .city(userEntityRequest.getCity())
                .postCode(userEntityRequest.getPostCode())
                .addressType(AddressType.HOME)
                .build();
        final Address address = addressMapper.map(addressRequest);
        address.setCreatedBy(user.getUsername());
        address.setCreatedDate(LocalDateTime.now());
        Long id = addressRepository.save(address).getId();
        Set<Role> roleSet = Collections.singleton(userRole);
        user.setRoles(roleSet);
        Set<Address> addresses = Collections.singleton(address);
        user.setAddresses(addresses);

        return mapper.mapToUserEntityResponse(userRepository.save(user));
    }

    @Override
    public UserEntityResponse getLoggedInUserDetails(Authentication authentication) {
        final UserEntity userEntity = userRepository.findByUsername(authentication.getName());
        return mapper.mapToUserEntityResponse(userEntity);
    }

    @Override
    public Boolean existsByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        return userEntity != null;
    }

    @Override
    public List<UserEntityResponse> getUsersByRole(String roleName) {
        List<UserEntity> userEntityList = userRepository.findByRole(roleName);
        return userEntityList.stream().map(mapper::mapToUserEntityResponse).toList();
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