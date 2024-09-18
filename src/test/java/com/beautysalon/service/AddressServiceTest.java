//package com.beautysalon.service;
//
//
//import com.beautysalon.address.Address;
//import com.beautysalon.address.AddressMapper;
//import com.beautysalon.address.AddressRepository;
//import com.beautysalon.address.AddressService;
//import com.beautysalon.address.dto.AddressRequest;
//import com.beautysalon.address.dto.AddressResponse;
//import com.beautysalon.role.Role;
//import com.beautysalon.user.User;
//import com.beautysalon.user.UserRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.security.core.Authentication;
//
//import java.util.ArrayList;
//import java.util.Collections;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class AddressServiceTest {
//
//    @Autowired
//    private AddressService service;
//    @Autowired
//    private AddressRepository addressRepository;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private AddressMapper mapper;
//    private Authentication loggedInUser;
//    private User user;
//
//    @BeforeEach
//    public void setup(){
//        addressRepository.deleteAll();
//        service = new AddressService(addressRepository,userRepository,mapper);
//        user = new User();
//        user.setId(10L);
//        user.setFirstName("Greg");
//        user.setLastName("Doe");
//        user.setEmail("greg@wp.pl");
//        user.setPassword("12345678");
//        user.setPhoneNumber("99999999");
//        user.setEnabled(true);
//        user.setRoleList(Collections.singletonList(new Role(1,"USER", new ArrayList<>())));
//    }
//
//    @Test
//    public void verifyIfAddressProperlySaved(){
//        //arrange
//        AddressRequest addressRequest = new AddressRequest("Hill Road", "London", "SP103TG","billing address",user);
//        //act
//        Address address = mapper.map(addressRequest);
//        Long id = service.saveAddress(addressRequest, );
//
//        //assert
//        Assertions.assertNotNull(id);
//        Assertions.assertEquals(addressRequest.street(), address.getStreet());
//        Assertions.assertEquals(addressRequest.city(), address.getCity());
//        Assertions.assertEquals(addressRequest.postCode(), address.getPostCode());
//        Assertions.assertEquals(addressRequest.addressType(), address.getAddressType());
//        Assertions.assertEquals(addressRequest.user(), address.getUserAddress());
//    }
//}
