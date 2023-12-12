package com.beautysalon.repository;

import com.beautysalon.repository.model.Booking;
import com.beautysalon.repository.model.ServiceType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

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
     * Retrieve bookings by date.
     *
     * @param date The date of the booking.
     * @return List of bookings with the given date.
     */
    @Query("SELECT b FROM Booking b WHERE b.date = ?1")
    List<Booking> findByDate(LocalDate date);


    /**
     * Retrieve bookings by service type.
     *
     * @param serviceType The service type of the booking.
     * @return List of bookings with the given service type.
     */
    @Query("SELECT b FROM Booking b WHERE b.serviceType = ?1")
    List<Booking> findByServiceType(ServiceType serviceType);

    /**
     * Retrieve bookings by email
     * @param email The clientEmail
     * @return Booking with the given email
     */
    @Query("select b FROM Booking  b where b.clientEmail = ?1")
    List<Booking> findResponseByEmail(String email);

}
