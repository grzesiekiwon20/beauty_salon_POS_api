package com.beautysalon.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {



}
