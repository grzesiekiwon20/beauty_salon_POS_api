package com.beautysalon.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {


    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UserEntity findByUsername(String username);
}
