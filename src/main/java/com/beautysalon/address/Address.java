package com.beautysalon.address;


import com.beautysalon.common.BaseEntity;
import com.beautysalon.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private String street;
    @NonNull
    private String city;
    @NonNull
    private String postCode;
    @NonNull
    private String addressType;

    private Long userId;

}
