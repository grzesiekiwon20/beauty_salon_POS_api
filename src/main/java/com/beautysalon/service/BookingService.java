package com.beautysalon.service;

import com.beautysalon.controller.dto.BookingRequest;
import com.beautysalon.controller.dto.BookingResponse;
import com.beautysalon.repository.BookingRepository;
import com.beautysalon.repository.ClientRepository;
import com.beautysalon.repository.model.Booking;
import com.beautysalon.repository.model.Client;
import com.beautysalon.repository.model.ServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository repository;
    private final ClientRepository clientRepository;

    public void save(final BookingRequest bookingRequest) {
        if (getBookingAvailability(bookingRequest)) {
            if (clientRepository.existsByEmail(bookingRequest.getClientEmail())) {
                final Client client = clientRepository.findByEmail(bookingRequest.getClientEmail());
                final Booking booking =
                        Booking.builder()
                                .date(bookingRequest.getDate())
                                .startTime(bookingRequest.getStartTime())
                                .finishTime(bookingRequest.getFinishTime())
                                .serviceType(bookingRequest.getServiceType())
                                .clientEmail(bookingRequest.getClientEmail())
                                .clientId(client.getId())
                                .build();

                repository.save(booking);
            } else {
                throw new RuntimeException("Booking can't be made without saving client first!");
            }
        } else {
            throw new RuntimeException("Booking times not available");
        }
    }

    public BookingResponse find(final Integer id) {
        return repository
                .findById(id)
                .map(booking ->
                        BookingResponse.builder()
                                .id(booking.getId())
                                .date(booking.getDate())
                                .startTime(booking.getStartTime())
                                .finishTime(booking.getFinishTime())
                                .serviceType(booking.getServiceType())
                                .clientEmail(booking.getClientEmail())
                                .clientId(booking.getClientId())
                                .build()
                )
                .orElseThrow(() -> new IllegalArgumentException("Booking with id: " + id + " not found"));
    }

    public List<Booking> findResponseByServiceType(ServiceType serviceType) {
        return repository.findByServiceType(serviceType);
    }

    public List<Booking> findResponseByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    public List<Booking> findByEmail(String email) {
        return repository.findResponseByEmail(email);
    }

    public List<Booking> findByStartTime(String startTime) {
        return repository.findResponseByStartTime(startTime);
    }

    public List<Booking> findAll() {
        return repository.findAll();
    }

    public List<Booking> findByFinishTime(String finishTime) {
        return repository.findResponseByFinishTime(finishTime);
    }

    public boolean getBookingAvailability(BookingRequest request) {
        List<Booking> bookings = repository.findByDate(request.getDate());
        final LocalTime start = LocalTime.parse(request.getStartTime());
        final LocalTime finish = LocalTime.parse(request.getFinishTime());

        for (Booking booking : bookings) {
            final LocalTime alreadyBookedStartTime = LocalTime.parse(booking.getStartTime());
            final LocalTime alreadyBookedFinishTime = LocalTime.parse(booking.getFinishTime());

            if (start.isAfter(alreadyBookedStartTime) && start.isBefore(alreadyBookedFinishTime)) {
                return false;
            }
            if (start.isBefore(alreadyBookedStartTime) && finish.isAfter(alreadyBookedFinishTime)) {
                return false;
            }
            if (finish.isAfter(alreadyBookedStartTime) && finish.isBefore(alreadyBookedFinishTime)) {
                return false;
            }
            if (start.equals(alreadyBookedStartTime)) {
                return false;
            }
            if (finish.isBefore(start)) {
                return false;
            }

        }
        return true;
    }
}
