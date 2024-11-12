package com.beautysalon.address;


import com.beautysalon.address.dto.AddressResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAddressesByUserId(String name);


    @Query("""
            select a from Address a where a.userId=:name and a.current=:current
            """)
    List<Address> findAddressesByCurrentIsAndId(@Param("current") boolean current, @Param("name") String name);
}
