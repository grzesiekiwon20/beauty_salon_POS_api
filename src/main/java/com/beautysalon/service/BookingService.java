package com.beautysalon.service;

import com.beautysalon.controller.dto.BookingRequest;
import com.beautysalon.controller.dto.BookingResponse;
import com.beautysalon.repository.model.booking.Booking;
import com.beautysalon.repository.model.ServiceType;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    void save(BookingRequest bookingRequest);

    BookingResponse find(Integer id);

    List<Booking> findAll();

    List<Booking> findByStartTime(String startTime);

    List<Booking> findByFinishTime(String finishTime);

    List<Booking> findByEmail(String email);

    List<Booking> findResponseByDate(LocalDate date);

    List<Booking> findResponseByServiceType(ServiceType serviceType);

    boolean getBookingAvailability(BookingRequest request);
}
