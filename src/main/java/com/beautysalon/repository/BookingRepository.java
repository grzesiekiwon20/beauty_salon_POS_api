package com.beautysalon.repository;

import com.beautysalon.repository.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {
}