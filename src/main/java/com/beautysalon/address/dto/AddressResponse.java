package com.beautysalon.address.dto;

import com.beautysalon.address.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse{

   private Long id;
   private String firstLineAddress;
   private String secondLineAddress;
   private String city;
   private String postCode;
   private AddressType addressType;
   private Boolean current;
   private String userId;

}
