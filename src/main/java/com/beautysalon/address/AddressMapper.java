package com.beautysalon.address;


import com.beautysalon.address.dto.AddressRequest;
import com.beautysalon.address.dto.AddressResponse;
import org.springframework.stereotype.Component;


@Component
public class AddressMapper {

    public Address map(AddressRequest request) {
        Address address = new Address();
        address.setFirstLineAddress(request.firstLineAddress());
        address.setSecondLineAddress(request.secondLineAddress());
        address.setCity(request.city());
        address.setPostCode(request.postCode());
        address.setAddressType(request.addressType());
        return address;
    }

    public AddressResponse map(Address address) {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setId(address.getId());
        addressResponse.setFirstLineAddress(address.getFirstLineAddress());
        addressResponse.setSecondLineAddress(address.getSecondLineAddress());
        addressResponse.setCity(address.getCity());
        addressResponse.setPostCode(address.getPostCode());
        addressResponse.setAddressType(address.getAddressType());
        addressResponse.setCurrent(address.getCurrent());
//        addressResponse.setAddressUserId(address.getAddressUserId());

        return addressResponse;
    }

}


