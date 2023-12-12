package com.beautysalon.repository;

import com.beautysalon.repository.model.Client;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Client entities.
 */
@Repository
public interface ClientRepository extends ListCrudRepository<Client, Integer> {

        /**
         * Retrieve a client by email.
         *
         * @param email The email of the client.
         * @return The client with the given email.
         */
        Client findByEmail(String email);

        /**
         * Retrieve a list of clients by name.
         *
         * @param name The name of the client.
         * @return List of clients with the given name.
         */
        Client findByName(String name);

        /**
         *
         * @param clientEmail The email of the client
         * @return boolean if client with given email already exists
         */
        boolean existsByEmail(String clientEmail);
}
