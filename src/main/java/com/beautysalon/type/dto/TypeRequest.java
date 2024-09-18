package com.beautysalon.type.dto;

public record TypeRequest(

        Long id,
        String name,
        String category,
        String description,
        String price,
        String duration,
        String activityImage ) {
}
