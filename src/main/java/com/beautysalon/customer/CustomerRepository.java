package com.beautysalon.customer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @Query(
            """
                    select c from Customer c where c.userId=:userId
                    """
    )
    Customer findByUserId(@Param("userId") String userId);
}
