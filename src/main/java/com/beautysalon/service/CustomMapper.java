package com.beautysalon.service;


import com.beautysalon.controller.dto.*;
import com.beautysalon.repository.model.Activity;
import com.beautysalon.repository.model.Address;
import com.beautysalon.repository.model.User;
import org.springframework.stereotype.Service;

@Service
public interface CustomMapper {

    //User mappers
    UserResponse map(User user);
    User map(UserRequest userRequest);

    //Activity mappers
    ActivityResponse map(Activity activity);
    Activity map(ActivityRequest request);

    //Address mappers
    AddressResponse map(Address address);
    Address map(AddressRequest request);

}
