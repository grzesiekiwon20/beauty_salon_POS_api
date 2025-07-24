package com.beautysalon.address;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {



@Query("""
select a from Address a where a.customer.id=:id
""")
    List<Address> findAddressesByCustomerId(@Param("id") Long id);

}
