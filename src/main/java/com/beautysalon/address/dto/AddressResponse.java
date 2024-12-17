package com.beautysalon.address.dto;

import com.beautysalon.address.AddressType;

public class AddressResponse{

   private Long id;
   private String firstLineAddress;
   private String secondLineAddress;
   private String city;
   private String postCode;
   private AddressType addressType;
   private Boolean current;
   private String addressUserId;

   public AddressResponse(Long id, String firstLineAddress, String secondLineAddress, String city, String postCode, AddressType addressType, Boolean current, String addressUserId) {
      this.id = id;
      this.firstLineAddress = firstLineAddress;
      this.secondLineAddress = secondLineAddress;
      this.city = city;
      this.postCode = postCode;
      this.addressType = addressType;
      this.current = current;
      this.addressUserId = addressUserId;
   }

   public AddressResponse() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstLineAddress() {
      return firstLineAddress;
   }

   public void setFirstLineAddress(String firstLineAddress) {
      this.firstLineAddress = firstLineAddress;
   }

   public String getSecondLineAddress() {
      return secondLineAddress;
   }

   public void setSecondLineAddress(String secondLineAddress) {
      this.secondLineAddress = secondLineAddress;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getPostCode() {
      return postCode;
   }

   public void setPostCode(String postCode) {
      this.postCode = postCode;
   }

   public AddressType getAddressType() {
      return addressType;
   }

   public void setAddressType(AddressType addressType) {
      this.addressType = addressType;
   }

   public Boolean getCurrent() {
      return current;
   }

   public void setCurrent(Boolean current) {
      this.current = current;
   }

   public String getAddressUserId() {
      return addressUserId;
   }

   public void setAddressUserId(String addressUserId) {
      this.addressUserId = addressUserId;
   }
}
