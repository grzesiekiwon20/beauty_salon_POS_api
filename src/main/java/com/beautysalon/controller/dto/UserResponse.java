package com.beautysalon.controller.dto;

import com.beautysalon.repository.model.UserType;
import lombok.*;

import java.util.List;


@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private UserType userType;
    private List<Long> activityList;
    private List<Long> addressList;
}
