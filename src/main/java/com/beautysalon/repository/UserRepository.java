package com.beautysalon.repository;


import com.beautysalon.repository.model.Address;
import com.beautysalon.repository.model.User;
import com.beautysalon.repository.model.UserType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends ListCrudRepository<User, Integer> {
    boolean existsByEmail(String email);

    boolean existsByName(String name);

    User findByEmail(String email);

    User findByName(String name);

    List<User> findByUserType(@NonNull UserType userType);

}
