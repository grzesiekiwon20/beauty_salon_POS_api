package com.beautysalon.repository;

import com.beautysalon.repository.model.Activity;
import com.beautysalon.repository.model.ServiceType;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Repository
public interface ActivityRepository extends ListCrudRepository<Activity, Integer> {


    List<Activity> findByServiceType(@NonNull ServiceType serviceType);

    List<Activity> findByDate(@NonNull LocalDate date);

    List<Activity> findByUserEmail(@NonNull String userEmail);

    List<Activity> findByStartTime(@NonNull LocalTime startTime);

    List<Activity> findByFinishTime(@NonNull LocalTime finishTime);
}
