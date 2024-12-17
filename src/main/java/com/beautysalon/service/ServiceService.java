package com.beautysalon.service;


import com.beautysalon.category.Category;
import com.beautysalon.category.CategoryRepository;
import com.beautysalon.file.FileStorageService;
import com.beautysalon.service.dto.ServiceRequest;
import com.beautysalon.service.dto.ServiceResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceService {


    private final ServiceMapper mapper;
    private final ServiceRepository serviceRepository;
    private final FileStorageService fileStorageService;
    private final CategoryRepository categoryRepository;

    public ServiceService(ServiceMapper mapper, ServiceRepository serviceRepository, FileStorageService fileStorageService, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.serviceRepository = serviceRepository;
        this.fileStorageService = fileStorageService;
        this.categoryRepository = categoryRepository;
    }

    public Long save(ServiceRequest serviceRequest, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new NullPointerException("No category found."));
        Service service =  mapper.map(serviceRequest);
        service.setCategory(category);
        return serviceRepository
                .save(service)
                .getId();
    }

    public List<ServiceResponse> findAllServices() {
        List<Service> services = serviceRepository.findAll();
        return services.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public ServiceResponse findById(Long id) {
        Service service = serviceRepository
                .findById(id)
                .orElseThrow(() -> new NullPointerException("No Type found"));
        return mapper.map(service);
    }


    public void uploadServiceCoverPicture(MultipartFile file, Long typeId) {
        Service service = serviceRepository.findById(typeId)
                .orElseThrow(() -> new EntityNotFoundException("No book found with ID:: " + typeId));
        var profilePicture = fileStorageService.saveFile(file, typeId);
        service.setImage(profilePicture);
        serviceRepository.save(service);
    }

    public List<ServiceResponse> findServicesByCategoryName(String categoryName) {
        return serviceRepository
                .findByCategoryName(categoryName)
                .stream()
                .map(mapper::map)
                .toList();
    }
}
