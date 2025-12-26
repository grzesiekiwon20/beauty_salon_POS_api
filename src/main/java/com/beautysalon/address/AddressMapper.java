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
                .firstLineAddress(request.firstLineAddress())
                .secondLineAddress(request.secondLineAddress())
                .city(request.city())
                .postCode(request.postCode())
                .addressType(request.addressType())
                .build();
    }

    public AddressResponse map(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .firstLineAddress(address.getFirstLineAddress())
                .secondLineAddress(address.getSecondLineAddress())
                .city(address.getCity())
                .postCode(address.getPostCode())
                .addressType(address.getAddressType())
                .userEntitySet(address.getUsers().stream().map(UserEntity::getUserId).collect(Collectors.toSet()))
                .build();
    }

}


