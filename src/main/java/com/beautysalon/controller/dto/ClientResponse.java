package com.beautysalon.controller.dto;


import com.beautysalon.repository.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientResponse {

        private Integer id;
        private String name;
        private String email;
        private Set<Integer> bookings;
}
