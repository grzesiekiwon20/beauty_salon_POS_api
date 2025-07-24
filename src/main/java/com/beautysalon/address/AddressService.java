package com.beautysalon.address;


import com.beautysalon.address.dto.AddressRequest;
import com.beautysalon.address.dto.AddressResponse;
import com.beautysalon.customer.Customer;
import com.beautysalon.customer.CustomerRepository;
import com.beautysalon.customer.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper mapper;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    public AddressService(AddressRepository addressRepository, AddressMapper mapper, CustomerService customerService, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    public Long saveAddress(AddressRequest request, Authentication connectedUser, Boolean current) {
        if (customerRepository.findCustomerByUserKeycloakId(connectedUser.getName()) == null) {
            Long userId = customerService.saveUserIntoRepository(connectedUser.getName());
        }
        Customer customer = customerRepository.findCustomerByUserKeycloakId(connectedUser.getName());
        List<Address> existingCurrentAddresses = addressRepository.findAddressesByCustomerId(customer.getId());
        if(!existingCurrentAddresses.isEmpty()){
            HashSet<AddressType> set = new HashSet<>();
            for(Address address : existingCurrentAddresses){
                if(address.getCurrent()) {
                    set.add(address.getAddressType());
                }
            }
            if(!set.contains(request.addressType())){
                Address created = mapper.map(request);
                created.setCurrent(current);
                created.setCustomer(customer);
                return addressRepository.save(created).getId();
            }
            if(set.size() > 3){
                throw new RuntimeException("You can not have more than three current addresses. Edit address or mark as expired");
            }
            else{
                throw new RuntimeException("Type of current address already exist, edit address or mark as expired");
            }
        }else{
            Address created = mapper.map(request);
            created.setCurrent(current);
            created.setCustomer(customer);
            return addressRepository.save(created).getId();
        }
    }

    public Long setAddressAsExpiredOrCurrent(Long id, boolean current){
        Address address = addressRepository.findById(id).orElseThrow(()-> new NullPointerException("No address found"));
        address.setCurrent(current);
        return addressRepository.save(address).getId();
    }

    public List<AddressResponse> getAllAddresses() {
        List<Address> addressList = addressRepository.findAll();
        return addressList
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public List<AddressResponse> getAddressById(Authentication connectedUser) {
        Customer customer = customerRepository.findCustomerByUserKeycloakId(connectedUser.getName());
        List<Address> addressList = addressRepository.findAddressesByCustomerId(customer.getId());
        return addressList.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

//    public List<AddressResponse> getNotCurrentAddressesOfAUser(Authentication connectedUser){
//        List<Address> addressList = addressRepository.findAddressesByUserId(connectedUser.getName());
//        return addressList
//                .stream()
//                .filter(address -> !address.getCurrent())
//                .map(mapper::map)
//                .collect(Collectors.toList());
//    }
    public Long updateExistingAddress(Long addressId, AddressRequest request) {
        Address existingAddress = addressRepository.findById(addressId).orElseThrow(() -> new NullPointerException("No Address Found"));
        existingAddress.setFirstLineAddress(request.firstLineAddress());
        existingAddress.setSecondLineAddress(request.secondLineAddress());
        existingAddress.setCity(request.city());
        existingAddress.setPostCode(request.postCode());
        existingAddress.setCurrent(request.current());
        return addressRepository.save(existingAddress).getId();
    }

    public AddressResponse findAddressResponseById(Long addressId) {
        Address address = addressRepository
                .findById(addressId)
                .orElseThrow(() -> new NullPointerException("No Address Found"));
        return mapper.map(address);
    }

    public void removeById(Long addressId) {
        if(addressRepository.findById(addressId) != null) {
            addressRepository.deleteById(addressId);
            System.out.println("Address has been removed.");
        }
        else {
            throw new EntityNotFoundException("No address found with id: " + addressId);
        }
    }



}
