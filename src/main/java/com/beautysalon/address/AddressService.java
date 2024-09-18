package com.beautysalon.address;


import com.beautysalon.address.dto.AddressRequest;
import com.beautysalon.address.dto.AddressResponse;
import com.beautysalon.user.User;
import com.beautysalon.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final AddressMapper mapper;

    public Long saveAddress(AddressRequest request, Authentication connectedUser) {
        Address newAddress = mapper.map(request);
        User user = (User) connectedUser.getPrincipal();
        User existingUser = userRepository.findById(user.getId()).orElseThrow(()-> new NullPointerException("No User Found"));
        newAddress.setUserId(existingUser.getId());
        return addressRepository.save(newAddress).getId();
    }

    public List<AddressResponse> getAllAddresses() {
        List<Address> addressList = addressRepository.findAll();
        return addressList
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public List<AddressResponse> getAddressById(Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        User existingUser = userRepository.findById(user.getId()).orElseThrow(()-> new NullPointerException("No User Found"));
        List<Address> addressList = existingUser.getAddresses();
        return addressList.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public Long updateExistingAddress(Long addressId, AddressRequest request) {
        Address existingAddress = addressRepository.findById(addressId).orElseThrow(()-> new NullPointerException("No Address Found"));
        existingAddress.setStreet(request.street());
        existingAddress.setCity(request.city());
        existingAddress.setPostCode(request.postCode());
        return addressRepository.save(existingAddress).getId();
    }

    public AddressResponse findAddressResponseById(Long addressId) {
        Address address = addressRepository
                .findById(addressId)
                .orElseThrow(()-> new NullPointerException("No Address Found"));
        return mapper.map(address);
    }


    public void removeById(Long addressId, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        User inRepositoryUser = verifyUser(user.getId());
        List<Address> addresses = inRepositoryUser.getAddresses();
        addresses.removeIf(address -> address.getId().equals(addressId));
        addressRepository.deleteById(addressId);
    }

    private User verifyUser(Long id){
        return userRepository.findById(id).orElseThrow(()-> new NullPointerException("No User Found"));
    }
}
