package com.beautysalon.customer;


import com.beautysalon.config.KeycloakAdminService;
import com.beautysalon.customer.dto.CustomerResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final KeycloakAdminService keycloakAdminService;
    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository customerRepository, KeycloakAdminService keycloakAdminService, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.keycloakAdminService = keycloakAdminService;
        this.mapper = mapper;
    }

    public CustomerResponse getUserDetailsForLoggedInUser(Authentication connectedUser) {
        Customer customer = customerRepository
                .findCustomerByUserKeycloakId(connectedUser.getName());
        return mapper
                .mapCustomerResponse(customer);
    }

    public List<CustomerResponse> findCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(mapper::mapCustomerResponse)
                .toList();
    }


    public Long saveUserIntoRepository(String id) {
        UserRepresentation userRepresentation = keycloakAdminService.getUserById(id);
        return customerRepository.save(mapper.map(userRepresentation)).getId();
    }

    public CustomerResponse getUserByIdFromCustomerRepository(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NullPointerException("No user found"));
        return mapper.mapCustomerResponse(customer);
    }

    public Boolean checkIfCustomerExist(String name) {
        return customerRepository.existsByUserKeycloakId(name);
    }
}
