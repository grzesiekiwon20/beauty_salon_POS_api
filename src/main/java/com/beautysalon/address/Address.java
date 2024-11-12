package com.beautysalon.address;


import com.beautysalon.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;




@Entity
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Address extends BaseEntity{


    @NonNull
    private String firstLineAddress;

    private String secondLineAddress;
    @NonNull
    private String city;
    @NonNull
    private String postCode;
    @NonNull
    private AddressType addressType;
    private Boolean current;

    private String userId;

}
