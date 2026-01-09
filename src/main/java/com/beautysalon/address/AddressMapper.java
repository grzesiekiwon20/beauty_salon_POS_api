package com.beautysalon.address;


import com.beautysalon.address.dto.AddressRequest;
import com.beautysalon.address.dto.AddressResponse;
import com.beautysalon.user.UserEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;


@Component
public class AddressMapper {

    public Address map(AddressRequest request) {

        return Address.builder()
                .street(request.getStreet())
                .city(request.getCity())
                .postCode(request.getPostCode())
                .addressType(request.getAddressType())
                .build();
    }

    public AddressResponse map(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .postCode(address.getPostCode())
                .addressType(address.getAddressType())
                .userEntitySet(address.getUsers().stream().map(UserEntity::getUserId).collect(Collectors.toSet()))
                .build();
    }

}


