package com.beautysalon.address;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {


//    @Query("""
//            select a from Address a where a.addressUserId=:name and a.current=:current
//            """)
//    List<Address> findAddressesByCurrentIsAndId(@Param("current") boolean current, @Param("name") String name);
//
//    @Query("""
//            select  a from Address  a where a.addressUserId=:name
//            """)
//    List<Address> findAddressesByUserId(@Param("name") String name);
}
