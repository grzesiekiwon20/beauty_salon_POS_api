package com.beautysalon.controller.dto;



import com.beautysalon.repository.model.UserType;
import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String name;
    private String email;
    private UserType userType;
}
