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
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository repository;
    private final ClientRepository clientRepository;

    public void save(final BookingRequest bookingRequest) {
        final Client client = clientRepository.findByEmail(bookingRequest.getClientEmail());
        final Booking booking =
                Booking.builder()
                        .date(bookingRequest.getDate())
                        .clientEmail(bookingRequest.getClientEmail())
                        .serviceType(bookingRequest.getServiceType())
                        .clientId(client.getId())
                        .build();
        if(clientRepository.existsByEmail(bookingRequest.getClientEmail())){
            repository.save(booking);
        }
        else {
            throw new RuntimeException("Booking can't be made without saving client first!");
        }
    }

    public BookingResponse find(final Integer id) {
        return repository
                .findById(id)
                .map(booking ->
                        BookingResponse.builder()
                                .id(booking.getId())
                                .date(booking.getDate())
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

    public List<Booking>findByEmail(String email) {
        return repository.findResponseByEmail(email);
    }
}
