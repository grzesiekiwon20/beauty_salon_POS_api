package com.beautysalon.address;


import com.beautysalon.common.BaseEntity;
import com.beautysalon.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Entity
@SuperBuilder
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity {

    private String street;
    private String city;
    private String postCode;
    private String addressType;

    @ManyToMany(mappedBy = "addresses")
    private List<User> userList;
}
