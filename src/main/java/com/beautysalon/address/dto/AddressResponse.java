package com.beautysalon.address.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class AddressResponse{

   private Long addressId;
   private String street;
   private String city;
   private String postCode;
   private String addressType;
   private List<Long> users;
}
