package com.beautysalon.service.impl;

import com.beautysalon.controller.dto.*;

import com.beautysalon.repository.model.*;
import com.beautysalon.repository.model.users.User;
import com.beautysalon.service.CustomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class CustomMapperImpl implements CustomMapper {



    //User mappers implementation
    @Override
    public User map(UserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.firstName())
                .lastName(userRequest.lastName())
                .email(userRequest.email())
                .phoneNumber(userRequest.phoneNumber())
                .loginDetails(userRequest.loginDetails())
                .userType(userRequest.userType())
                .activities(new ArrayList<>())
                .addresses(new ArrayList<>())
                .enabled(userRequest.enabled())
                .build();
    }

    @Override
    public UserResponse map(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .loginDetails(user.getLoginDetails())
                .userType(user.getUserType())
                .activities(user.getActivities().stream().map(Activity::getId).collect(Collectors.toList()))
                .addresses(user.getAddresses().stream().map(Address::getId).collect(Collectors.toList()))
                .enabled(user.isEnabled())
                .build();
    }

    //Activity mapper implementation
    @Override
    public ActivityResponse map(Activity activity) {
        return ActivityResponse.builder()
                .id(activity.getId())
                .date(activity.getDate())
                .startTime(activity.getStartTime())
                .finishTime(activity.getFinishTime())
                .serviceType(activity.getServiceType())
                .taskDone(activity.isTaskDone())
                .users(activity.getUsers().stream().map(User::getId).collect(Collectors.toList()))
                .build();
    }

    @Override
    public Activity map(ActivityRequest request) {
        return Activity.builder()
                .date(request.date())
                .startTime(request.startTime())
                .finishTime(request.finishTime())
                .serviceType(request.serviceType())
                .taskDone(request.taskDone())
                .users(new ArrayList<>())
                .build();
    }

    //Address mapper implementation
    @Override
    public AddressResponse map(Address address) {
        return AddressResponse.builder()
                .addressId(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .postCode(address.getPostCode())
                .addressType(address.getAddressType())
                .users(address.getUsers().stream().map(User::getId).collect(Collectors.toList()))
                .build();
    }

    @Override
    public Address map(AddressRequest request) {
        return Address.builder()
                .street(request.street())
                .city(request.city())
                .postCode(request.postCode())
                .addressType(request.addressType())
                .users(new ArrayList<>())
                .build();
    }

}
