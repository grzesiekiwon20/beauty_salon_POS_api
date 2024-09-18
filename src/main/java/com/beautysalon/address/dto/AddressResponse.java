package com.beautysalon.address.dto;

import com.beautysalon.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse{

   private Long id;
   private String street;
   private String city;
   private String postCode;
   private String addressType;
   private Long userId;

}
