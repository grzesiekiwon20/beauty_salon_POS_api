package com.beautysalon.user;


import com.beautysalon.address.Address;
import com.beautysalon.address.AddressType;
import com.beautysalon.exception.EmailAlreadyExistsException;
import com.beautysalon.exception.UsernameAlreadyExistsException;
import com.beautysalon.role.Role;
import com.beautysalon.role.RoleRepository;
import com.beautysalon.user.dto.UserEntityRequest;
import com.beautysalon.user.dto.UserEntityResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserMapper mapper;

    @Mock
    private PasswordEncoder passwordEncoder;


    private UserEntity userEntityTest;
    private UserEntityRequest userEntityRequestTest;
    private UserEntityResponse userEntityResponseTest;
    private Role role;
    private Validator validator;

    private static final ValidatorFactory factory = buildDefaultValidatorFactory();

    @BeforeEach
    void setup() {
        String password = "Username1234!?%";
        String email = "username@gmail.com";
        String name = "Username20";
        String userId = "username123423414-qerokasf";
        this.userEntityRequestTest = new UserEntityRequest(name, password, email);
        this.role = new Role(1L, "USER", new HashSet<>());
        Address address = Address.builder()
                .firstLineAddress("FirstLine")
                .secondLineAddress("SecondLine")
                .city("City")
                .postCode("09656")
                .addressType(AddressType.HOME)
                .build();
        this.userEntityResponseTest = UserEntityResponse.builder()
                .userId(userId)
                .username(name)
                .email(email)
                .password("$2a$10$u8mhv549vxcNteKzKO8cZeXAlebLuQbzt8btkXzSam2IJ.5IhCS6.")
                .locked(false)
                .enabled(true)
                .roles(Collections.singleton(1L))
                .addresses(Collections.singleton(1L))
                .build();
        this.userEntityTest = UserEntity.builder()
                .userId(userId)
                .username(name)
                .email(email)
                .password(password)
                .locked(false)
                .enabled(true)
                .roles(Collections.singleton(role))
                .addresses(Collections.singleton(address))
                .build();

        this.role.setUsers(Collections.singleton(this.userEntityTest));
        address.setUsers(Collections.singleton(this.userEntityTest));
        validator = factory.getValidator();
    }
    @AfterEach
    void close(){
        factory.close();
    }

    @Nested
    @DisplayName("Register users tests")
    class RegisterUserTests {

        @Test
        @DisplayName("Should register user successfully")
        void shouldRegisterUserSuccessfully() {
            // Arrange
            String rawPassword = userEntityRequestTest.password();
            String encodedPassword = "$2a$10$u8mhv549vxcNteKzKO8cZeXAlebLuQbzt8btkXzSam2IJ.5IhCS6.";


            when(roleRepository.findByName("USER")).thenReturn(Optional.of(role));
            when(mapper.toEntity(userEntityRequestTest)).thenReturn(userEntityTest);
            when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);
            when(userRepository.existsByUsername(userEntityRequestTest.username())).thenReturn(false);
            when(userRepository.existsByEmail(userEntityRequestTest.email())).thenReturn(false);
            when(userRepository.save(any(UserEntity.class))).thenReturn(userEntityTest);
            when(mapper.mapToUserEntityResponse(userEntityTest)).thenReturn(userEntityResponseTest);

            // Act
            UserEntityResponse response = userServiceImpl.registerUser(userEntityRequestTest);

            // Assert

            assertThat(response.password()).isEqualTo(encodedPassword);
            assertThat(response).isNotNull();
            assertThat(response.enabled()).isTrue();
            assertThat(response.locked()).isFalse();
            assertThat(response.roles()).containsExactly(1L);
            assertThat(response.addresses()).isNotNull();

            // Verify
            verify(roleRepository).findByName("USER");
            verify(passwordEncoder, times(1)).encode(rawPassword);
            verify(userRepository, times(1)).save(any(UserEntity.class));
            verify(userRepository, times(1)).existsByEmail(userEntityTest.getEmail());
            verify(userRepository, times(1)).existsByUsername(userEntityTest.getUsername());
            verify(mapper, times(1)).toEntity(userEntityRequestTest);
            verify(mapper,times(1)).mapToUserEntityResponse(userEntityTest);
        }

        @Test
        @DisplayName("Should throw exception when user request is null")
        void shouldThrowExceptionWhenUserRequestIsNull() {

            final IllegalArgumentException exception = Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> userServiceImpl.registerUser(null)
            );

            assertThat(exception).isNotNull();
            assertThat(exception.getMessage()).contains("null");
        }

        @Test
        @DisplayName("Should throw exception when user with given username already exists")
        void shouldThrowExceptionWhenUserWithUsernameAlreadyExists() {

            when(userRepository.existsByUsername(userEntityRequestTest.username())).thenReturn(true);

            final UsernameAlreadyExistsException exception = Assertions.assertThrows(
                    UsernameAlreadyExistsException.class,
                    () -> userServiceImpl.registerUser(userEntityRequestTest)
            );

            assertThat(exception).isNotNull();
            assertThat(exception.getMessage()).contains("Username already taken");
        }
        @Test
        @DisplayName("Should throw exception when user with given email already exists")
        void shouldThrowExceptionWhenUserWithEmailAlreadyExists() {

            when(userRepository.existsByEmail(userEntityRequestTest.email())).thenReturn(true);

            final EmailAlreadyExistsException exception = Assertions.assertThrows(
                    EmailAlreadyExistsException.class,
                    () -> userServiceImpl.registerUser(userEntityRequestTest)
            );

            assertThat(exception).isNotNull();
            assertThat(exception.getMessage()).contains("Email already registered");
        }

    }
    @Nested
    @DisplayName("Find users tests")
    class FindUserTests{

        @Test
        @DisplayName("Should throw exception when user with given username is not found")
        void shouldThrowExceptionWhenUserWithGivenUsernameIsNotFound() {

            when(userRepository.existsByUsername(userEntityRequestTest.username())).thenReturn(false);

            final UsernameNotFoundException exception = Assertions.assertThrows(
                    UsernameNotFoundException.class,
                    () -> userServiceImpl.loadUserByUsername(userEntityRequestTest.username())
            );

            assertThat(exception).isNotNull();
            assertThat(exception.getMessage()).contains("User not found");

            verify(userRepository, times(1)).existsByUsername(userEntityRequestTest.username());
        }
        @Test
        @DisplayName("Should find user by username")
        void shouldFindUserByUsername(){

            when(userRepository.existsByUsername(userEntityRequestTest.username())).thenReturn(true);
            when(userRepository.findByUsername(userEntityRequestTest.username())).thenReturn(userEntityTest);

            UserDetails user = userServiceImpl.loadUserByUsername(userEntityRequestTest.username());
            assertThat(user).isNotNull();
            assertThat(user.getAuthorities()).isNotEmpty();
            assertThat(user.getAuthorities()).extracting(GrantedAuthority::getAuthority).contains("ROLE_USER");

        }
    }

    @Nested
    @DisplayName("Input validation tests")
    class InputValidationTests{


        @Test
        @DisplayName("Verifies if validation works properly when user request with given username is correct")
        void verifiesIfExceptionIsThrownWhenCategoryRequestWithGivenNameTooLong() {
            final String username = "Username2012";

            UserEntityRequest userEntityRequest = new UserEntityRequest(username, "Username20231!", "username@gmail.com");
            Set<ConstraintViolation<UserEntityRequest>> violationSet = validator.validate(userEntityRequest);

            assertThat(violationSet).isEmpty();
        }

        @Test
        @DisplayName("Verifies if validation works properly when user request with given username has illegal characters")
        void VerifiesIfValidationWorksProperlyWhenUserRequestWithGivenUsernameHasIllegalCharacters() {

            final String username = "user___";
            final UserEntityRequest userEntityRequest = new UserEntityRequest(username, "Username20231!", "username@gmail.com");
            Set<ConstraintViolation<UserEntityRequest>> violationSet = validator.validate(userEntityRequest);


            assertThat(violationSet).isNotEmpty();
        }
        @Test
        @DisplayName("Verifies if validation works properly when user request with given username is too long")
        void VerifiesIfValidationWorksProperlyWhenUserRequestWithGivenUsernameIsTooLong() {

            final String username = "usernameggggggggggbgvggggggggggggggggggggggggggggggggg";
            final UserEntityRequest userEntityRequest = new UserEntityRequest(username, "Username20231", "username@gmail.com");
            Set<ConstraintViolation<UserEntityRequest>> violationSet = validator.validate(userEntityRequest);

            assertThat(violationSet).isNotEmpty();
        }

        @Test
        @DisplayName("Verifies if validation works properly when user request with password given is too short")
        void VerifiesIfValidationWorksProperlyWhenUserRequestWithPasswordGivenIsTooShort() {

            final String password = "User20";
            final UserEntityRequest userEntityRequest = new UserEntityRequest("Username20", password, "username@gmail.com");
            Set<ConstraintViolation<UserEntityRequest>> violationSet = validator.validate(userEntityRequest);

            assertThat(violationSet).isNotEmpty();
        }
        @Test
        @DisplayName("Verifies if validation works properly when user request with password given has no specialCharacters")
        void VerifiesIfValidationWorksProperlyWhenUserRequestWithPasswordGivenHasNoSpecialCharacters() {

            final String password = "Username20";
            final UserEntityRequest userEntityRequest = new UserEntityRequest("Username20", password, "username@gmail.com");
            Set<ConstraintViolation<UserEntityRequest>> violationSet = validator.validate(userEntityRequest);

            assertThat(violationSet).isNotEmpty();
        }
        @Test
        @DisplayName("Verifies if validation works properly when user request with incorrect email address")
        void VerifiesIfValidationWorksProperlyWhenUserRequestWithIncorrectEmailAddress() {

            final String email = "usernamegmail.com";
            final UserEntityRequest userEntityRequest = new UserEntityRequest("Username20", "Username20231!", email);
            Set<ConstraintViolation<UserEntityRequest>> violationSet = validator.validate(userEntityRequest);

            assertThat(violationSet).isNotEmpty();
        }
    }
}
