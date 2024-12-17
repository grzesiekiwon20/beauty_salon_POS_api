package com.beautysalon.service;

import com.beautysalon.service.dto.ServiceResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {



    @Query("""
select s from Service s where s.category.name=:categoryName
""")
    List<Service> findByCategoryName(@Param("categoryName") String categoryName);
}
