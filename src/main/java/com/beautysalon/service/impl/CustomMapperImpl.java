package com.beautysalon.service.impl;

import com.beautysalon.controller.dto.*;
import com.beautysalon.repository.model.Activity;
import com.beautysalon.repository.model.Address;
import com.beautysalon.repository.model.User;
import com.beautysalon.service.CustomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class CustomMapperImpl implements CustomMapper {


    //User mapper implementation
    @Override
    public UserResponse map(final User user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .userType(user.getUserType())
                .activityList(user.getActivities()
                        .stream()
                        .map(Activity::getId)
                        .collect(Collectors.toList()))
                .addressList(user.getAddresses()
                        .stream()
                        .map(Address::getId)
                        .collect(Collectors.toList()))
                .build();
    }
    @Override
    public User map(final UserRequest request) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .userType(request.getUserType())
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
                .userType(activity.getUserType())
                .taskDone(activity.isTaskDone())
                .user(activity.getUser())
                .build();
    }

    @Override
    public Activity map(ActivityRequest request) {
        return Activity.builder()
                .date(request.getDate())
                .startTime(request.getStartTime())
                .finishTime(request.getFinishTime())
                .serviceType(request.getServiceType())
                .userType(request.getUserType())
                .taskDone(request.isTaskDone())
                .user(request.getUser())
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
                .user(address.getUser())
                .build();
    }

    @Override
    public Address map(AddressRequest request) {
        return Address.builder()
                .street(request.getStreet())
                .city(request.getCity())
                .postCode(request.getPostCode())
                .addressType(request.getAddressType())
                .user(request.getUser())
                .build();
    }


}
