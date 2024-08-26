package com.beautysalon.repository;



import com.beautysalon.repository.model.users.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {
    boolean existsByEmail(String email);


    User findByEmail(String email);

    User findByLoginDetails_Username(String username);

}
