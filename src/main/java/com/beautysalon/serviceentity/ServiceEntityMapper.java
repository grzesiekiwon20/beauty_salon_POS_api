package com.beautysalon.serviceentity;


import com.beautysalon.file.FileUtils;
import com.beautysalon.serviceentity.dto.ServiceEntityRequest;
import com.beautysalon.serviceentity.dto.ServiceEntityResponse;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class ServiceEntityMapper {

    public ServiceEntity map(ServiceEntityRequest request){
       return ServiceEntity.builder()
               .name(request.getName())
               .description(request.getDescription())
               .price(request.getPrice())
               .duration(request.getDuration())
               .image(request.getImage())
               .build();
    }

    public ServiceEntityResponse map(ServiceEntity serviceEntity){
        return ServiceEntityResponse.builder()
                .id(serviceEntity.getId())
                .name(serviceEntity.getName())
                .description(serviceEntity.getDescription())
                .price(serviceEntity.getPrice())
                .duration(serviceEntity.getDuration())
                .image(FileUtils.readFileFromLocation(serviceEntity.getImage()))
                .categoryName(serviceEntity.getCategory().getName())
                .build();
    }
}
