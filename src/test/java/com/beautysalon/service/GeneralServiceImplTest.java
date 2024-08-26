package com.beautysalon.service;


import com.beautysalon.repository.ActivityRepository;
import com.beautysalon.repository.AddressRepository;
import com.beautysalon.repository.UserRepository;
import com.beautysalon.service.impl.CustomMapperImpl;
import com.beautysalon.service.impl.GeneralServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class GeneralServiceImplTest {

    @Autowired
    private GeneralServiceImpl service;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private CustomMapperImpl mapper;
    @Autowired
    private AddressRepository addressRepository;


    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        activityRepository.deleteAll();
        addressRepository.deleteAll();
    }

//    @Test
//    void verifyIfServiceFindByEmailMethodFindsUserResponse(){
//        //Arrange
//        final UserRequest userRequest = new UserRequest("Greg I", "greg@wp.pl", UserType.ADMINISTRATOR, new ArrayList<>(),new ArrayList<>());
//
//        //Act
//        service.save(userRequest);
//        final UserResponse expectedUserResponse = service.findUserResponseByEmail("greg@wp.pl");
//
//        //Assert
//        assertNotNull(expectedUserResponse);
//        assertEquals(userRequest.getEmail(), expectedUserResponse.getEmail());
//
//    }
//    @Test
//    void testMapperMapsCorrectly(){
//        //Arrange
//        final UserRequest userRequest = new UserRequest("Greg I", "greg@wp.pl", UserType.ADMINISTRATOR,new ArrayList<>(),new ArrayList<>());
//
//        //Act
//        final User userToBeSaved = mapper.map(userRequest);
//        userRepository.save(userToBeSaved);
//        final User userSaved = userRepository.findByEmail(userToBeSaved.getEmail());
//
//        assertNotNull(userSaved.getId());
//        assertEquals(userRequest.getName() , userSaved.getName());
//        assertEquals(userRequest.getEmail() , userSaved.getEmail());
//        assertEquals(userRequest.getUserType(), userSaved.getUserType());
//        assertNotNull(userSaved.getActivities().stream().map(Activity::getId));
//        assertNotNull(userSaved.getAddresses().stream().map(Address::getId));
//
//    }
//
//    @Test
//    void testServiceSaveMethodSavesUserCorrectly() {
//        // Arrange
//        final UserRequest userRequest = new UserRequest("Greg I", "greg@wp.pl", UserType.CLIENT,new ArrayList<>(),new ArrayList<>());
//
//        // Act
//        service.save(userRequest);
//
//        final User savedUser = userRepository.findByEmail("greg@wp.pl");
//
//        // Assert
//        Assertions.assertNotNull(savedUser);
//        Assertions.assertEquals(savedUser.getEmail(), userRequest.getEmail());
//
//    }
//
//    @Test
//    void verifyIfSaveMethodSavesTheSameUserTwice() {
//        //Arrange
//        final UserRequest firstUserRequest = new UserRequest("Greg I", "greg@wp.pl", UserType.CLIENT,new ArrayList<>(),new ArrayList<>());
//
//        //Act
//        service.save(firstUserRequest);
//
//        //Assert
//        Assertions.assertThrows(RuntimeException.class, ()-> service.save(firstUserRequest));
//
//    }
//
//    @Test
//    void verifyIfSaveMethodSavesUserWithNullEmail() {
//        //Arrange
//        final UserRequest userRequest = new UserRequest("Greg I", null, UserType.CLIENT,new ArrayList<>(),new ArrayList<>());
//
//        //Assert
//        assertThrows(NullPointerException.class, () -> service.save(userRequest));
//
//    }
//
//    @Test
//    void verifyIfSaveMethodSavesUserWithEmailLengthZero() {
//        //Arrange
//        final UserRequest userRequest = new UserRequest("Greg I", "", UserType.CLIENT,new ArrayList<>(),new ArrayList<>());
//
//        //Assert
//        assertThrows(RuntimeException.class, () -> service.save(userRequest));
//
//    }
//
//    @Test
//    void verifyIfSaveMethodSavesUserWithIncorrectEmailFormat() {
//        //Arrange
//        final UserRequest userRequest = new UserRequest("Greg I", "invalid-email", UserType.CLIENT,new ArrayList<>(),new ArrayList<>());
//
//        //Assert
//        assertThrows(RuntimeException.class, () -> service.save(userRequest));
//    }
//    @Test
//    void verifyIfSaveMethodSavesUserWithNullName() {
//        //Arrange
//        final UserRequest userRequest = new UserRequest(null, "greg@wp.pl", UserType.CLIENT,new ArrayList<>(),new ArrayList<>());
//
//        //Assert
//        assertThrows(NullPointerException.class, () -> service.save(userRequest));
//
//    }
//    @Test
//    void verifyIfSaveMethodSavesUserWithEmailToLong(){
//        final String email = "asdgasfgdfsfgsdfgsdfgsdfgdsafkghjaksjfhgkjasdhfglkjhsdfkjghsdfjhgklsjdghkjsdghfkshdgfkjhsdfk@adkfuhasdkjhfgasdjhfgajkshdgfjhasdgfjhasgdfljhagsdfjhgasdljhfgasdjhfgjhsadfgjhsdfgjaajhdfgjahsgfjhasdgfhjgasdhjfgashjdfgjhasdgfjhasgdfjhasdgfjhsagdfjhgasfjhgsadfjhfgsdkjhagfdjhahjsdgfjhgasdfjhgasdhjfafdfdsfasdfasdf.pl";
//        final UserRequest userRequest = new UserRequest("Greg", email, UserType.EMPLOYEE,new ArrayList<>(),new ArrayList<>());
//
//        assertThrows(RuntimeException.class,() -> service.save(userRequest));
//    }
//    @Test
//    void verifyIfSaveMethodSavesUserWithNullServiceType(){
//        //Arrange
//        final UserRequest userRequest = new UserRequest("Greg I", "greg@wp.pl", null,new ArrayList<>(),new ArrayList<>());
//
//        //Assert
//        assertThrows(NullPointerException.class, () -> service.save(userRequest));
//    }
//
//
//
//    @Test
//    void verifyIfSaveMethodSavesUserWithSameNameButDifferentEmail(){
//        //Arrange
//        final UserRequest userOneRequest = new UserRequest("Greg I", "greg@wp.pl", UserType.EMPLOYEE,new ArrayList<>(),new ArrayList<>());
//        final UserRequest userTwoRequest = new UserRequest("Greg I", "grzesiek@wp.pl", UserType.EMPLOYEE,new ArrayList<>(),new ArrayList<>());
//
//        //Act
//        service.save(userOneRequest);
//        service.save(userTwoRequest);
//
//        final User savedUserOne = userRepository.findByEmail(userOneRequest.getEmail());
//        final User savedUserTwo = userRepository.findByEmail(userTwoRequest.getEmail());
//
//        //Assert
//        assertNotNull(savedUserOne.getId());
//        assertNotNull(savedUserTwo.getId());
//        assertNotEquals(savedUserOne.getId(), savedUserTwo.getId());
//    }
}
