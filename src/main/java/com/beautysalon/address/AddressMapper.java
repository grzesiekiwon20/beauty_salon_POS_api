package com.beautysalon.address;


import com.beautysalon.address.dto.AddressRequest;
import com.beautysalon.address.dto.AddressResponse;
import org.springframework.stereotype.Component;


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


