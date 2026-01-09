package com.beautysalon.address;


import com.beautysalon.common.BaseEntity;
import com.beautysalon.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "addresses")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address extends BaseEntity{


    private String street;
    private String city;
    private String postCode;
    private AddressType addressType;

    @ManyToMany(mappedBy = "addresses")
    private Set<UserEntity> users;

}
