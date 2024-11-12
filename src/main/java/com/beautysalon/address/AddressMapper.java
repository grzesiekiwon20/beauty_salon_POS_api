package com.beautysalon.address;


import com.beautysalon.address.dto.AddressRequest;
import com.beautysalon.address.dto.AddressResponse;
import org.springframework.stereotype.Component;


@Component
public class AddressMapper {

    public Address map(AddressRequest request){
        return Address.builder()
                .firstLineAddress(request.firstLineAddress())
                .secondLineAddress(request.secondLineAddress())
                .city(request.city())
                .postCode(request.postCode())
                .addressType(request.addressType())
                .current(request.current())
                .userId(request.userId())
                .build();
    }

    public AddressResponse map(Address address){
        return AddressResponse.builder()
                .id(address.getId())
                .firstLineAddress(address.getFirstLineAddress())
                .secondLineAddress(address.getSecondLineAddress())
                .city(address.getCity())
                .postCode(address.getPostCode())
                .addressType(address.getAddressType())
                .current(address.getCurrent())
                .userId(address.getUserId())
                .build();
    }

}


