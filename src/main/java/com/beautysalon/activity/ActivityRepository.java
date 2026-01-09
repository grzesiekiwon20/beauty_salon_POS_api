package com.beautysalon.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("""
            select a from Activity a join a.userEntityList u where u.userId=:id and a.date=:date
            """)
    List<Activity> findActivitiesByEmployeeIdAndDate(@Param("id") String id, @Param("date") LocalDate date);
}
