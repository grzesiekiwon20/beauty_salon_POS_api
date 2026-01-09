package com.beautysalon.address;

import com.beautysalon.address.dto.AddressRequest;
import com.beautysalon.address.dto.AddressResponse;
import com.beautysalon.common.MessageResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

interface AddressService {

    MessageResponse saveAddress(AddressRequest request, Authentication connectedUser);
    List<AddressResponse> getAddressResponsesListFromRepositoryForConnectedUser(Authentication connectedUser);
    AddressResponse getAddressResponseByAddressId(Long addressId);
    MessageResponse removeAddressByAddressId(Long addressId);
    AddressResponse findAddressResponseById(Long addressId);
    AddressResponse findUsersHomeAddress(Authentication authentication);
    MessageResponse updateExistingAddress(Long addressId, String firstLineAddress, String secondLineAddress, String city, String postcode);
}
