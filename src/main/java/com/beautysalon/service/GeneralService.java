package com.beautysalon.service;


import com.beautysalon.controller.dto.*;
import com.beautysalon.repository.model.ServiceType;
import com.beautysalon.repository.model.UserType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface GeneralService {

    //UserService methods
    void save(UserRequest request);
    UserResponse findUserResponseByEmail(String userEmail);
    UserResponse findUserResponseById(Long userId);
    void deleteUserById(Long userId);
    List<UserResponse> findListOfUserResponses();
    UserResponse findUserResponseByName(String userName);
    List<UserResponse> findListOfUserResponsesByUserType(UserType userType);


    // ActivityService methods
    void save(ActivityRequest request);
    ActivityResponse findActivityById(Integer id);
    List<ActivityResponse> findAllActivities();
    List<ActivityResponse> findListOfActivityResponsesByStartTime(String startTime);
    List<ActivityResponse> findListOfActivityResponsesByFinishTime(String finishTime);
    List<ActivityResponse> findListOfActivityResponsesByEmail(String email);
    List<ActivityResponse> findListOfActivityResponsesByDateAndUserType(LocalDate date , UserType userType);
    List<ActivityResponse> findActivityResponsesByServiceTypeAndUserType(ServiceType serviceType , UserType userType);
    boolean getBookingAvailability(ActivityRequest request);

    //AddressService methods
    void save(AddressRequest request);
}
