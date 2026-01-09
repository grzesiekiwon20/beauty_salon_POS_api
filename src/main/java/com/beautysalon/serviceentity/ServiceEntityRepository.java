package com.beautysalon.serviceentity;

import com.beautysalon.serviceentity.dto.ServiceEntityResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceEntityRepository extends JpaRepository<ServiceEntity, Long> {


    @Query("""
            select s from ServiceEntity s join s.category c where c.id=:categoryId
            """)
    List<ServiceEntity> findByCategoryId(@Param("categoryId") Long categoryId);
}