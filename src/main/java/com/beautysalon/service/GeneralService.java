package com.beautysalon.service;


import com.beautysalon.controller.dto.*;
import com.beautysalon.repository.model.Activity;
import com.beautysalon.repository.model.Address;
import com.beautysalon.repository.model.ServiceType;
import com.beautysalon.repository.model.UserType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GeneralService {

    //UserService methods
    void saveUser(UserRequest request);

    UserResponse findUserResponseByEmail(String userEmail);

    UserResponse findUserResponseById(Long userId);

    void deleteUserById(Long userId);

    List<UserResponse> findListOfUserResponses();

    List<UserResponse> findListOfUserResponsesByUserType(UserType userType);

    UserResponse userResponseByUsername(String username);



    // ActivityService methods
    void saveActivity(ActivityRequest activityRequest);

    Optional<Activity> assignUsersToActivity(Long activityId, List<Long> userIds);

    List<ActivityResponse> findAllActivityResponses();

    List<ActivityResponse> findListOfActivityResponsesByStartTime(String startTime);

    List<ActivityResponse> findListOfActivityResponsesByFinishTime(String finishTime);

    List<ActivityResponse> findListOfActivityResponsesByEmail(String email);
    List<ActivityResponse> findListOfActivityResponseByServiceType(ServiceType serviceType);

    boolean getActivityAvailability(ActivityRequest request);

    //AddressService methods
    void saveAddress(AddressRequest request);

    Optional<Address> addressAssignment(final Long addressId, final List<Long> clientIds);


    List<ActivityResponse> findListOfActivityResponsesByUsername(String username);
}
