package com.beautysalon.repository;

import com.beautysalon.repository.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientRepository extends ListCrudRepository<Client, Integer> {

        Client findByEmail(String email);
        List<Client> findByName(String name);

        Page<Client> findByName(String name, Pageable pageable);
}
