package com.beautysalon.controller.dto;

import com.beautysalon.repository.model.AddressType;
import com.beautysalon.repository.model.User;
import lombok.Builder;
import java.util.List;

@Builder
public record AddressRequest (

    String street,
    String city,
    String postCode,
    AddressType addressType,
    List<User> users
) {}
