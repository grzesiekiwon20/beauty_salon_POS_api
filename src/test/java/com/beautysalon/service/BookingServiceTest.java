package com.beautysalon.service;

import com.beautysalon.controller.dto.BookingRequest;
import com.beautysalon.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;

public class BookingServiceTest {

    private BookingRepository repository;
    private BookingService service;

    @BeforeEach
    public void setup(){
        repository = mock(BookingRepository.class);
        service = mock(BookingService.class);
    }

    @Test
    public void verifyIfBookingTimesAreAvailableWithCorrectParams(){
        BookingRequest bookingRequest = mock(BookingRequest.class);
        when(service.getBookingAvailability(bookingRequest)).thenReturn(true);

        service.save(bookingRequest);

        verify(service, times(1)).save(bookingRequest);
    }
    @Test
    public void verifyIfBookingTimesAreAvailableWithIncorrectParams(){
        BookingRequest bookingRequest = mock(BookingRequest.class);
        when(service.getBookingAvailability(bookingRequest)).thenReturn(false);

        service.save(bookingRequest);

        verify(service, times(1)).save(bookingRequest);
    }
    @Test
    public void verifyIfExceptionIsThrownIfNoBookingTimeIsAvailable(){

    }

}
