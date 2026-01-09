package com.beautysalon.webmvc;

import com.beautysalon.product.ProductServiceImpl;
import com.beautysalon.product.dto.ProductResponse;
import jakarta.annotation.Nonnull;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

public class ProductFormatter implements Formatter<ProductResponse> {

    @Autowired
    private ProductServiceImpl productService;


    public ProductFormatter(){
        super();
    }

    @Override
    public ProductResponse parse(@NonNull String text, @NonNull Locale locale) throws ParseException {
        final Long productId = Long.parseLong(text);
        return this.productService.findProductResponseByProductId(productId);
    }


    @Override
    @Nonnull
    public String print(ProductResponse object, @NonNull Locale locale) {
        return (object != null? object.id().toString() : "");
    }
}
