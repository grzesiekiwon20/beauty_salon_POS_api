package com.beautysalon.service;

import com.beautysalon.controller.dto.ClientRequest;
import com.beautysalon.controller.dto.ClientResponse;
import com.beautysalon.repository.model.Address;
import com.beautysalon.repository.model.Booking;
import com.beautysalon.repository.model.Client;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ClientMapper {

    public ClientResponse map(Client client) {
        return ClientResponse
                .builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .bookings(
                        client
                                .getBookings()
                                .stream()
                                .map(Booking::getId)
                                .collect(Collectors.toList()))
                .addressList(
                        client
                                .getAddressList()
                                .stream()
                                .map(Address::getId)
                                .collect(Collectors.toList()))
                .build();
    }

    public Client map(ClientRequest clientRequest) {
        return Client
                .builder()
                .name(clientRequest.getName())
                .email(clientRequest.getEmail())
                .build();
    }
}
