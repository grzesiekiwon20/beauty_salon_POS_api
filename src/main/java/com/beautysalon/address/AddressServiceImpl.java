package com.beautysalon.address;


import com.beautysalon.address.dto.AddressRequest;
import com.beautysalon.address.dto.AddressResponse;
import com.beautysalon.common.MessageResponse;
import com.beautysalon.user.UserEntity;
import com.beautysalon.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper mapper;
    private final UserRepository userRepository;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper mapper, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Override
    public MessageResponse saveAddress(final AddressRequest request,final Authentication connectedUser) {
        Address address = mapper.map(request);
        final UserEntity user = userRepository.findByUsername(connectedUser.getName());
        if (address.getUsers() == null) {
            address.setUsers(new HashSet<>());
        }
        if(addressRepository.getAddressesByUsername(user.getUsername())!= null) {
            final List<Address> addressList = addressRepository.getAddressesByUsername(user.getUsername());
            for (Address existingAddress : addressList) {
                if (Objects.equals(existingAddress.getAddressType(), request.addressType())) {
                    return new MessageResponse("You cannot have more than one address with the same type. You can edit existing or remove and add another one.");
                }
            }
        }
        user.getAddresses().add(address);
        addressRepository.save(address);
        return new MessageResponse("Address saved successfully.");
    }

    @Override
    public AddressResponse findAddressResponseById(final Long addressId) {
        final Address address = addressRepository
                .findById(addressId)
                .orElseThrow(() -> new NullPointerException("No Address Found"));
        return mapper.map(address);
    }

    @Override
    public MessageResponse updateExistingAddress(Long addressId, String firstLineAddress, String secondLineAddress, String city, String postcode) {
        Address address = addressRepository.findById(addressId).orElseThrow(
                ()-> new EntityNotFoundException("No address found with id: "+ addressId));
        if(firstLineAddress != null){
            address.setFirstLineAddress(firstLineAddress);
        }
        if(secondLineAddress!= null){
            address.setSecondLineAddress(secondLineAddress);
        }
        if(city!= null){
            address.setCity(city);
        }
        if(postcode != null){
            address.setPostCode(postcode);
        }

        Address updatedAddress = addressRepository.save(address);

        return new MessageResponse("Address updated successfully");
    }

    @Override
    public MessageResponse removeAddressByAddressId(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(()-> new EntityNotFoundException("Address not found with id: " + addressId));
        UserEntity user = userRepository.findUserByAddressId(addressId);
        user.getAddresses().remove(address);
        addressRepository.delete(address);
        return new MessageResponse("Address removed successfully");
    }


    @Override
    public List<AddressResponse> getAddressResponsesListFromRepositoryForConnectedUser(Authentication connectedUser) {
        List<Address> addressList = addressRepository.getAddressesByUsername(connectedUser.getName());
        return addressList.stream().map(mapper::map).toList();
    }

    @Override
    public AddressResponse getAddressResponseByAddressId(Long addressId) {
        return null;
    }


}
