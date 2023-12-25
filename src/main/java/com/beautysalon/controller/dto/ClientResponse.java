package com.beautysalon.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientResponse {

        private Integer id;
        private String name;
        private String email;
        private List<Integer> bookings;
        private List<Integer> addressList;
}
