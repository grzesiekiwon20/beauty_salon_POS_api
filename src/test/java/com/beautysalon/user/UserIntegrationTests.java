package com.beautysalon.user;


import com.beautysalon.address.Address;
import com.beautysalon.address.AddressMapper;
import com.beautysalon.address.AddressRepository;
import com.beautysalon.address.AddressType;
import com.beautysalon.address.dto.AddressRequest;
import com.beautysalon.role.Role;
import com.beautysalon.role.RoleRepository;
import com.beautysalon.user.dto.UserEntityRequest;
import com.beautysalon.user.dto.UserEntityResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserIntegrationTests {

    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private AddressRequest addressRequestTest;
    private UserEntity userEntityTest;
    private UserEntityRequest userEntityRequestTest;
    private UserEntityResponse userEntityResponseTest;
    private Address addressTest;
    private Role role;

    @BeforeEach
    void setup() {
        this.userService = new UserServiceImpl(userRepository, userMapper, addressMapper, roleRepository, addressRepository);
        this.role = new Role(null,"USER", new HashSet<>());
        this.roleRepository.save(this.role);

        String password = "Username1234!?%";
        String confirm = "Username1234!?%";
        String email = "username@gmail.com";
        String username = "Username20";
        String userId = "username123423414-qerokasf";
        String fullName = "Full Name";
        String phone = "654757674";
        String street = "Street";
        String city = "city";
        String postCode = "Post Code";
        this.userEntityRequestTest = UserEntityRequest.builder()
                .username(username)
                .password(password)
                .confirm(confirm)
                .email(email)
                .fullName(fullName)
                .phone(phone)
                .street(street)
                .city(city)
                .postCode(postCode)
                .build();
        this.addressRequestTest = AddressRequest.builder()
                .street(street)
                .city(city)
                .postCode(postCode)
                .addressType(AddressType.HOME)
                .build();
        this.addressTest = Address.builder()
                .street(street)
                .city(city)
                .postCode(postCode)
                .addressType(AddressType.HOME)
                .build();
//        this.userEntityResponseTest = UserEntityResponse.builder()
//                .userId(userId)
//                .username(username)
//                .email(email)
//                .password("$2a$10$u8mhv549vxcNteKzKO8cZeXAlebLuQbzt8btkXzSam2IJ.5IhCS6.")
//                .locked(false)
//                .enabled(true)
//                .roles(Collections.singleton(role))
//                .addresses(Collections.singleton(1L))
//                .build();
//        this.userEntityTest = UserEntity.builder()
//                .userId(userId)
//                .username(username)
//                .email(email)
//                .password(password)
//                .locked(false)
//                .enabled(true)
//                .roles(Collections.singleton(role))
//                .addresses(Collections.singleton(addressTest))
//                .build();

    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        addressRepository.deleteAll();
    }

    @Nested
    @DisplayName("Create user integration tests")
    class CreateUserIntegrationTest {


        @Test
        @DisplayName("Should create user successfully")
        void shouldCreateUserSuccessfully() {

            UserEntityResponse userEntityResponse = userService.registerUser(userEntityRequestTest, passwordEncoder);

            assertNotNull(userEntityResponse);
            assertThat(userEntityResponse.roles()).isNotEmpty();
            assertThat(userEntityResponse.roles()).hasSize(1);
            assertThat(userEntityResponse.password()).isNotEqualTo(userEntityRequestTest.getPassword());
            assertThat(userEntityResponse.username()).isEqualTo(userEntityRequestTest.getUsername());
            assertThat(userEntityResponse.addresses()).isNotEmpty();
        }
    }
}
