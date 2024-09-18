package com.beautysalon.type;


import com.beautysalon.file.FileUtils;
import com.beautysalon.type.dto.TypeRequest;
import com.beautysalon.type.dto.TypeResponse;
import org.springframework.stereotype.Component;

@Component
public class TypeMapper {

    public Type map(TypeRequest request){
        return Type.builder()
                .id(request.id())
                .name(request.name())
                .category(request.category())
                .description(request.description())
                .price(request.price())
                .duration(request.duration())
                .build();
    }

    public TypeResponse map(Type type){
         return TypeResponse.builder()
                 .id(type.getId())
                 .name(type.getName())
                 .category(type.getCategory())
                 .description(type.getDescription())
                 .price(type.getPrice())
                 .duration(type.getDuration())
                 .activityImage(FileUtils.readFileFromLocation(type.getActivityImage()))
                .build();
    }
}
