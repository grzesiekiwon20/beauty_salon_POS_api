package com.beautysalon.type;


import com.beautysalon.type.dto.TypeRequest;
import com.beautysalon.type.dto.TypeResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Type", description = "The Type Api")
@RestController
@RequiredArgsConstructor
@RequestMapping("types")
public class TypeController {

    private final TypeService typeService;

    @PostMapping("/create")
    public ResponseEntity<Long> createType(
           @Valid @RequestBody TypeRequest typeRequest
    ){
        return ResponseEntity.ok(typeService.save(typeRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TypeResponse>> getAllTypes(){
        return ResponseEntity.ok(typeService.findAllTypes());
    }

    @GetMapping("typeId/{id}")
    public ResponseEntity<TypeResponse> getTypeById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(typeService.findById(id));
    }


    @PostMapping(value = "/cover/{type-id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadTypeCoverPicture(
            @PathVariable("type-id") Long typeId,
            @Parameter()
            @RequestPart("file") MultipartFile file
    ) {
        typeService.uploadTypeCoverPicture(file, typeId);
        return ResponseEntity.accepted().build();
    }
}
