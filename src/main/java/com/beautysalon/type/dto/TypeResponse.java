package com.beautysalon.type.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class TypeResponse {

    private Long id;
    private String name;
    private String category;
    private String description;
    private String price;
    private String duration;
    private byte[] activityImage;
 }
