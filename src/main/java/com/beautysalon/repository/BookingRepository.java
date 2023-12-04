package com.beautysalon.repository;

import com.beautysalon.repository.model.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Booking entities.
 */
@Repository
public interface BookingRepository extends ListCrudRepository<Booking, Integer> {

    /**
     * Retrieve a list of bookings by ID.
     *
     * @param id The ID of the booking.
     * @return List of bookings with the given ID.
     */
    @Query("SELECT b FROM Booking b WHERE b.id = ?1")
    List<Booking> findById(int id);

    /**
     * Retrieve a booking by date.
     *
     * @param date The date of the booking.
     * @return Optional containing the booking with the given date (if exists).
     */
    @Query("SELECT b FROM Booking b WHERE b.date = ?1")
    Optional<Booking> findByDate(String date);

    /**
     * Retrieve a booking by start time.
     *
     * @param startTime The start time of the booking.
     * @return Optional containing the booking with the given start time (if exists).
     */
    @Query("SELECT b FROM Booking b WHERE b.startTime = ?1")
    Optional<Booking> findByStartTime(String startTime);

    /**
     * Retrieve a list of bookings by end time.
     *
     * @param endTime The end time of the booking.
     * @return List of bookings with the given end time.
     */
    @Query("SELECT b FROM Booking b WHERE b.endTime = ?1")
    List<Booking> findByEndTime(String endTime);

    /**
     * Retrieve a list of bookings by service type.
     *
     * @param serviceType The service type of the booking.
     * @return List of bookings with the given service type.
     */
    @Query("SELECT b FROM Booking b WHERE b.serviceType = ?1")
    List<Booking> findByServiceType(String serviceType);
}
