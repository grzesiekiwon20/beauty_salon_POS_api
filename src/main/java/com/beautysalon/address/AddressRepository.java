package com.beautysalon.address;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {


    @Query(value = """
    select a from Address a join  a.users u where u.username=:username
""")
    List<Address> getAddressesByUsername(String username);
}