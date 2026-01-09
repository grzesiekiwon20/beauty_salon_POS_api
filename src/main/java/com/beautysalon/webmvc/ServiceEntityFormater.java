package com.beautysalon.webmvc;

import com.beautysalon.serviceentity.ServiceEntityServiceImpl;
import com.beautysalon.serviceentity.dto.ServiceEntityResponse;
import jakarta.annotation.Nonnull;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ServiceEntityFormater implements Formatter<ServiceEntityResponse> {

    @Autowired
    private ServiceEntityServiceImpl serviceEntityService;


    public ServiceEntityFormater(){
        super();
    }
    @Override
    public ServiceEntityResponse parse(@NonNull String text, @NonNull Locale locale) throws ParseException {
        final Long serviceEntityId = Long.parseLong(text);
        return this.serviceEntityService.findById(serviceEntityId);
    }


    @Override
    @Nonnull
    public String print(ServiceEntityResponse object, @NonNull Locale locale) {
        return (object != null? object.id().toString() : "");
    }
}
