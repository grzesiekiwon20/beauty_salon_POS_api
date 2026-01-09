package com.beautysalon.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {


    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UserEntity findByUsername(String username);


    @Query("""
            select u from UserEntity u join u.addresses a where a.id=:addressId
            """)
    UserEntity findUserByAddressId(@Param("addressId") Long addressId);

    @Query("""
            select u from UserEntity u join u.roles r where r.name=:roleName
            """)
    List<UserEntity> findByRole(@Param("roleName") String roleName);
}
