package com.beautysalon.service;

import com.beautysalon.controller.dto.BookingRequest;
import com.beautysalon.controller.dto.BookingResponse;
import com.beautysalon.repository.BookingRepository;
import com.beautysalon.repository.ClientRepository;
import com.beautysalon.repository.EmployeeRepository;
import com.beautysalon.repository.model.Booking;
import com.beautysalon.repository.model.Client;
import com.beautysalon.repository.model.Employee;
import com.beautysalon.repository.model.ServiceType;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository repository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    public void save(final BookingRequest bookingRequest) {
        if (getBookingAvailability(bookingRequest)) {
            if (clientRepository.existsByEmail(bookingRequest.getClientEmail())) {
                final Client client = clientRepository.findByEmail(bookingRequest.getClientEmail());
                final Employee employee = employeeRepository.findByName(bookingRequest.getEmployeeName());
                final Booking booking =
                        Booking.builder()
                                .date(bookingRequest.getDate())
                                .startTime(bookingRequest.getStartTime())
                                .finishTime(bookingRequest.getFinishTime())
                                .serviceType(bookingRequest.getServiceType())
                                .clientEmail(bookingRequest.getClientEmail())
                                .employeeName(bookingRequest.getEmployeeName())
                                .clientId(client.getId())
                                .employeeId(employee.getId())
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
        final LocalTime start = LocalTime.parse(startTime);
        return repository.findResponseByStartTime(start);
    }

    public List<Booking> findAll() {
        return repository.findAll();
    }

    public List<Booking> findByFinishTime(String finishTime) {
        final LocalTime finish = LocalTime.parse(finishTime);
        return repository.findResponseByFinishTime(finish);
    }

    public boolean getBookingAvailability(BookingRequest request) {
        List<Booking> bookings = repository.findAll();
        final LocalTime start = request.getStartTime();
        final LocalTime finish = request.getFinishTime();
        final LocalDate date  = request.getDate();

        for (Booking booking : bookings) {
            if(booking.getDate().equals(date) && booking.getEmployeeName().equals(request.getEmployeeName())) {
                final LocalTime alreadyBookedStartTime = booking.getStartTime();
                final LocalTime alreadyBookedFinishTime = booking.getFinishTime();

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
        }
        return true;
    }
}
