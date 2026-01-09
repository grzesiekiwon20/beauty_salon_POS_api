package com.beautysalon.serviceentity;


import com.beautysalon.category.Category;
import com.beautysalon.category.CategoryRepository;
import com.beautysalon.file.FileStorageService;
import com.beautysalon.serviceentity.dto.ServiceEntityRequest;
import com.beautysalon.serviceentity.dto.ServiceEntityResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceEntityServiceImpl {


    private final ServiceEntityMapper mapper;
    private final ServiceEntityRepository serviceEntityRepository;
    private final FileStorageService fileStorageService;
    private final CategoryRepository categoryRepository;


    public void save(ServiceEntityRequest serviceEntityRequest, final MultipartFile file) {
        Category category = categoryRepository.findById(serviceEntityRequest.getCategory().getId()).orElseThrow(()-> new NullPointerException("No category found."));
        ServiceEntity serviceEntity =  mapper.map(serviceEntityRequest);
        serviceEntity.setCategory(category);
        final ServiceEntity savedServiceEntity = serviceEntityRepository.save(serviceEntity);
        final Long serviceEntityId = savedServiceEntity.getId();
        if(!file.isEmpty()){
            String fileName = fileStorageService.saveFile(file, serviceEntityId);
            savedServiceEntity.setImage(fileName);
            serviceEntityRepository.save(savedServiceEntity);
        }
    }

    public List<ServiceEntityResponse> findAllServices() {
        List<ServiceEntity> serviceEntities = serviceEntityRepository.findAll();
        return serviceEntities.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public ServiceEntityResponse findById(Long id) {
        ServiceEntity serviceEntity = serviceEntityRepository
                .findById(id)
                .orElseThrow(() -> new NullPointerException("No Type found"));
        return mapper.map(serviceEntity);
    }


    public void uploadServiceCoverPicture(MultipartFile file, Long typeId) {
        ServiceEntity serviceEntity = serviceEntityRepository.findById(typeId)
                .orElseThrow(() -> new EntityNotFoundException("No book found with ID:: " + typeId));
        var profilePicture = fileStorageService.saveFile(file, typeId);
        serviceEntity.setImage(profilePicture);
        serviceEntityRepository.save(serviceEntity);
    }

    public List<ServiceEntityResponse> findServicesByCategoryId(Long categoryId) {
        return serviceEntityRepository
                .findByCategoryId(categoryId)
                .stream()
                .map(mapper::map)
                .toList();
    }
}
