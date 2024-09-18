package com.beautysalon.address;


import com.beautysalon.address.dto.AddressRequest;
import com.beautysalon.address.dto.AddressResponse;
import com.beautysalon.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class AddressMapper {

    public Address map(AddressRequest request){
        return Address.builder()
                .street(request.street())
                .city(request.city())
                .postCode(request.postCode())
                .addressType(request.addressType())
                .userId(request.userId())
                .build();
    }

    public AddressResponse map(Address address){
        return AddressResponse.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .postCode(address.getPostCode())
                .addressType(address.getAddressType())
                .userId(address.getUserId())
                .build();
    }

}


