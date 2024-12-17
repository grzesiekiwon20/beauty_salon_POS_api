package com.beautysalon.customer;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByUserKeycloakId(String userKeycloakId);

    Boolean existsByUserKeycloakId(String name);
}
