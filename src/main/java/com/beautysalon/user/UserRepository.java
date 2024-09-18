package com.beautysalon.user;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE CONCAT(u.firstName, ' ', u.lastName) = :fullName")
    Optional<User> findByFullName(@Param("fullName") String fullName);

    boolean existsByEmail(String email);
}
