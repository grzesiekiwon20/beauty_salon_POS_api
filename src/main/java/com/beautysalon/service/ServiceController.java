package com.beautysalon.service;


import com.beautysalon.service.dto.ServiceRequest;
import com.beautysalon.service.dto.ServiceResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Service", description = "The Service Api")
@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }


    @PostMapping("/create/{categoryId}")
    public ResponseEntity<Long> createService(
           @Valid @RequestBody ServiceRequest serviceRequest,
           @PathVariable Long categoryId
    ){
        return ResponseEntity.ok(serviceService.save(serviceRequest, categoryId));
    }
@GetMapping("/byCategory")
public ResponseEntity<List<ServiceResponse>> getServicesByCategory(
        @RequestParam String categoryName
){
return ResponseEntity.ok(serviceService.findServicesByCategoryName(categoryName));
}
    @GetMapping("/all")
    public ResponseEntity<List<ServiceResponse>> getAllServices(){
        return ResponseEntity.ok(serviceService.findAllServices());
    }

    @GetMapping("serviceId/{id}")
    public ResponseEntity<ServiceResponse> getServiceById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(serviceService.findById(id));
    }


    @PostMapping(value = "/cover/{service-id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadServiceCoverPicture(
            @PathVariable("service-id") Long serviceId,
            @Parameter()
            @RequestPart("file") MultipartFile file
    ) {
        serviceService.uploadServiceCoverPicture(file, serviceId);
        return ResponseEntity.accepted().build();
    }
}
