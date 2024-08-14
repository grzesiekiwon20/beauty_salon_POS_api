package com.beautysalon.repository;


import com.beautysalon.repository.model.Address;
import com.beautysalon.repository.model.AddressType;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends ListCrudRepository<Address, Integer> {

}
