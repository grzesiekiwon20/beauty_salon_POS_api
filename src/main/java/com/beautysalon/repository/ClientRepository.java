package com.beautysalon.repository;

import com.beautysalon.repository.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        List<Client> findByName(String name);

        /**
         * Retrieve a page of clients by name.
         *
         * @param name     The name of the client.
         * @param pageable The pagination information.
         * @return Page of clients with the given name.
         */
        Page<Client> findByName(String name, Pageable pageable);
}
