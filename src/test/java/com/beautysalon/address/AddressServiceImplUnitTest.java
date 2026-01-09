package com.beautysalon.address;


import com.beautysalon.address.dto.AddressRequest;
import com.beautysalon.address.dto.AddressResponse;
import com.beautysalon.common.MessageResponse;
import com.beautysalon.role.Role;
import com.beautysalon.user.UserEntity;
import com.beautysalon.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceImplUnitTest {

    @InjectMocks
    private AddressServiceImpl addressServiceImpl;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressMapper mapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    private Address addressTest;
    private AddressRequest addressRequestTest;
    private AddressResponse addressResponseTest;
    private UserEntity userEntity;

    @BeforeEach
    void setup() {
        String street = "firstLineAddress 10";
        String city = "City";
        String postCode = "PO032";
        AddressType addressType = AddressType.HOME;
        this.userEntity = UserEntity.builder()
                .userId("82089493-5463-4cf4-bee3-cc8eabbe1aa9")
                .username("Username20")
                .password("Password20!")
                .email("username@gmail.com")
                .enabled(true)
                .locked(false)
                .roles(Collections.singleton(new Role(1L, "USER", new HashSet<>())))
                .addresses(new HashSet<>())
                .build();
        this.addressTest = Address.builder()
                .street(street)
                .city(city)
                .postCode(postCode)
                .addressType(addressType)
                .users(Collections.singleton(this.userEntity))
                .build();
        this.addressRequestTest = AddressRequest.builder()
                .street(street)
                .city(city)
                .postCode(postCode)
                .addressType(addressType)
                .build();
        this.addressResponseTest = AddressResponse.builder()
                .street(street)
                .city(city)
                .postCode(postCode)
                .addressType(addressType)
                .userEntitySet(Collections.singleton(userEntity.getUserId()))
                .build();
    }

    @Nested
    @DisplayName("Create address tests")
    class CreateAddressTests {

        @Test
        @DisplayName("Should create address with correct inputs")
        void shouldCreateAddressWithCorrectInputs() {
            String authenticationName = "Username20";

            List<Address> addressList = Collections.singletonList(addressTest);
            when(authentication.getName()).thenReturn(authenticationName);
            when(mapper.map(addressRequestTest)).thenReturn(addressTest);
            when(userRepository.findByUsername(authenticationName)).thenReturn(userEntity);
            when(addressRepository.save(any(Address.class))).thenReturn(addressTest);

            MessageResponse messageResponse = addressServiceImpl.saveAddress(addressRequestTest, authentication);

            assertNotNull(messageResponse);
            assertThat(messageResponse.message()).isEqualTo("Address saved successfully.");
            verify(authentication, times(1)).getName();
            verify(mapper, times(1)).map(addressRequestTest);
            verify(userRepository, times(1)).findByUsername(userEntity.getUsername());
            verify(addressRepository, times(1)).save(any(Address.class));
        }
        @Test
        @DisplayName("Should create message response that you can not have more than one address with the same type")
        void shouldCreateMessageResponseThatYouCannotHaveMoreThanOneAddressWithTheSameType() {
            String authenticationName = "Username20";
            List<Address> addressList = Collections.singletonList(addressTest);

            when(authentication.getName()).thenReturn(authenticationName);
            when(mapper.map(addressRequestTest)).thenReturn(addressTest);
            when(userRepository.findByUsername(authenticationName)).thenReturn(userEntity);
            when(addressRepository.getAddressesByUsername(userEntity.getUsername())).thenReturn(addressList);
//            Act
            MessageResponse messageResponse = addressServiceImpl.saveAddress(addressRequestTest, authentication);
//            Assert
            assertNotNull(messageResponse);
            assertThat(messageResponse.message()).isEqualTo("You cannot have more than one address with the same type. You can edit existing or remove and add another one.");
            verify(authentication, times(1)).getName();
            verify(mapper, times(1)).map(addressRequestTest);
            verify(userRepository, times(1)).findByUsername(userEntity.getUsername());
            verifyNoMoreInteractions(addressRepository);
        }
        @Test
        @DisplayName("Should update address successfully with no null values")
        void shouldUpdateAddressSuccessfullyWithNoNullValues() {
            String street = "Street 201";
            String cityTest = "CityTest";
            String postCodeTest = "PostCode-12";
            Long id = 1L;
            Address updatedAddress = Address.builder()
                    .street(street)
                    .city(cityTest)
                    .postCode(postCodeTest)
                    .addressType(AddressType.HOME)
                    .build();

            when(addressRepository.findById(id)).thenReturn(Optional.of(addressTest));
            when(addressRepository.save(any(Address.class))).thenReturn(updatedAddress);

//            MessageResponse messageResponse = addressServiceImpl.updateExistingAddress(id, firstLineAddressTest,secondLineAddressTest,cityTest,postCodeTest);

//            assertThat(messageResponse).isNotNull();
//            assertThat(messageResponse.message()).isEqualTo("Address updated successfully");
//            assertThat(addressTest.getAddressType()).isEqualTo(updatedAddress.getAddressType());
//            assertThat(addressTest.getFirstLineAddress()).isEqualTo(updatedAddress.getFirstLineAddress());
//            assertThat(addressTest.getSecondLineAddress()).isEqualTo(updatedAddress.getSecondLineAddress());
//            assertThat(addressTest.getCity()).isEqualTo(updatedAddress.getCity());
//            assertThat(addressTest.getPostCode()).isEqualTo(updatedAddress.getPostCode());

//            verify(addressRepository,times(1)).findById(id);
//            verify(addressRepository,times(1)).save(any(Address.class));

        }
        @Test
        @DisplayName("Should update address successfully with some null values")
        void shouldUpdateAddressSuccessfullyWithSomeNullValues() {
            String street = "Street 201";
            String postCodeTest = "PostCode-12";
            Long id = 1L;
            Address updatedAddress = Address.builder()
                    .street(street)
                    .city(null)
                    .postCode(postCodeTest)
                    .addressType(AddressType.HOME)
                    .build();

            when(addressRepository.findById(id)).thenReturn(Optional.of(addressTest));
            when(addressRepository.save(any(Address.class))).thenReturn(updatedAddress);

//            MessageResponse messageResponse = addressServiceImpl.updateExistingAddress(id, firstLineAddressTest,null,null,postCodeTest);

//            assertThat(messageResponse).isNotNull();
//            assertThat(messageResponse.message()).isEqualTo("Address updated successfully");
//            assertThat(addressTest.getAddressType()).isEqualTo(updatedAddress.getAddressType());
//            assertThat(addressTest.getFirstLineAddress()).isEqualTo(updatedAddress.getFirstLineAddress());
//            assertThat(addressTest.getSecondLineAddress()).isEqualTo("secondLineAddress 2");
//            assertThat(addressTest.getCity()).isEqualTo("City");
//            assertThat(addressTest.getPostCode()).isEqualTo(updatedAddress.getPostCode());
//
//            verify(addressRepository,times(1)).findById(id);
//            verify(addressRepository,times(1)).save(any(Address.class));
        }
        @Test
        @DisplayName("Should throw exception when method updateExistingAddress receives null address id")
        void shouldThrowExceptionWhenMethodUpdateExistingAddressReceivesNullAddressId(){
            String firstLineAddressTest = "Street 201";
            String secondLineAddressTest = "Street 201";
            String cityTest = "City20";
            String postCodeTest = "PostCode-12";

            EntityNotFoundException exception = assertThrows(
                    EntityNotFoundException.class,
                    ()-> addressServiceImpl.updateExistingAddress(null,firstLineAddressTest, secondLineAddressTest, cityTest, postCodeTest)
            );

            assertThat(exception).isNotNull();
            assertThat(exception.getMessage()).contains("No address");

            verify(addressRepository, times(1)).findById(null);
        }
    }
}
