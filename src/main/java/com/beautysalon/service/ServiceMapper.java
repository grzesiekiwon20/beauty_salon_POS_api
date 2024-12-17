package com.beautysalon.service;


import com.beautysalon.file.FileUtils;
import com.beautysalon.service.dto.ServiceRequest;
import com.beautysalon.service.dto.ServiceResponse;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class ServiceMapper {

    public Service map(ServiceRequest request){
        Service service = new Service();
        service.setName(request.name());
        service.setDescription(request.description());
        service.setPrice(request.price());
        service.setDuration(LocalTime.parse(request.duration(), DateTimeFormatter.ofPattern("HH:mm:ss")));
        service.setImage(request.image());
        return service;
    }

    public ServiceResponse map(Service service){
         ServiceResponse serviceResponse = new ServiceResponse();
         serviceResponse.setId(service.getId());
         serviceResponse.setName(service.getName());
         serviceResponse.setCategoryName(service.getCategory().getName());
         serviceResponse.setCategoryId(service.getCategory().getId());
         serviceResponse.setDescription(service.getDescription());
         serviceResponse.setPrice(service.getPrice());
         serviceResponse.setDuration(service.getDuration());
         serviceResponse.setImage(FileUtils.readFileFromLocation(service.getImage()));
         return serviceResponse;
    }
}
