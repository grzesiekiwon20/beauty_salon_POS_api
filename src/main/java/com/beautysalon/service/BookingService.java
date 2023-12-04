package com.beautysalon.service;

import com.beautysalon.controller.dto.BookingRequest;
import com.beautysalon.repository.BookingRepository;
import com.beautysalon.repository.model.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository repository;

    public void save(final BookingRequest bookingRequest) {
        final Booking booking = Booking
                .builder()
                .date(bookingRequest.getDate())
                .startTime(bookingRequest.getStartTime())
                .endTime(bookingRequest.getEndTime())
                .serviceType(bookingRequest.getServiceType())
                .build();
        repository.save(booking);
    }

    public List<Booking> findResponseById(Long id) {
        return repository.findById(id.intValue());
    }

    public List<Booking> findResponseByServiceType(String serviceType) {
        return repository.findByServiceType(serviceType);
    }

    public List<Booking> findResponseByDate(String date) {
        return repository.findByDate(date);
    }
    public List<Booking> findResponseByEndTime(String endTime) {
        return repository.findByEndTime(endTime);
    }

    public List<Booking> findResponseByStartTime(String startTime) {
        return repository.findByStartTime(startTime);
    }
}
