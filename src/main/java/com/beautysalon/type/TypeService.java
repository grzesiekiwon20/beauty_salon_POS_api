package com.beautysalon.type;


import com.beautysalon.file.FileStorageService;
import com.beautysalon.type.dto.TypeRequest;
import com.beautysalon.type.dto.TypeResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TypeService {


    private final TypeMapper mapper;
    private final TypeRepository typeRepository;
    private final FileStorageService fileStorageService;

    public Long save(TypeRequest typeRequest) {
        return typeRepository
                .save(mapper.map(typeRequest))
                .getId();
    }

    public List<TypeResponse> findAllTypes() {
        List<Type> types = typeRepository.findAll();
        return types.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public TypeResponse findById(Long id) {
        Type type = typeRepository
                .findById(id)
                .orElseThrow(() -> new NullPointerException("No Type found"));
        return mapper.map(type);
    }


    public void uploadTypeCoverPicture(MultipartFile file, Long typeId) {
        Type type = typeRepository.findById(typeId)
                .orElseThrow(() -> new EntityNotFoundException("No book found with ID:: " + typeId));
        var profilePicture = fileStorageService.saveFile(file, typeId);
        type.setActivityImage(profilePicture);
        typeRepository.save(type);
    }
}
