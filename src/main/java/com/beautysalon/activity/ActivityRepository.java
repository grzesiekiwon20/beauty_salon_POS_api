package com.beautysalon.activity;

import com.beautysalon.activity.dto.ActivityResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {


    List<Activity> findActivitiesByEmployeeId(Long employeeId);

    @Query("""
            select a from Activity a where a.employee.id=:employeeId and a.date=:date
            """)
    List<Activity> findActivitiesByEmployeeIdAndDate(@Param("employeeId") Long employeeId,@Param("date") LocalDate date);

    List<Activity> findActivityByCustomerId(Long id);
}
