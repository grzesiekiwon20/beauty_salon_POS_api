package com.beautysalon.product;


import com.beautysalon.file.FileUtils;
import com.beautysalon.product.dto.ProductRequest;
import com.beautysalon.product.dto.ProductResponse;
import org.springframework.stereotype.Component;

@Component
class ProductMapper {


    protected Product mapProduct(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .image(productRequest.getImage())
                .discount(productRequest.getDiscount())
                .stockQuantity(productRequest.getStockQuantity())
                .specialPrice(productRequest.getSpecialPrice())
                .category(productRequest.getCategory())
                .build();
    }

    protected ProductResponse mapProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(FileUtils.readFileFromLocation(product.getImage()))
                .discount(product.getDiscount())
                .specialPrice(product.getSpecialPrice())
                .category(product.getCategory())
                .stockQuantity(product.getStockQuantity())
                .build();
    }

}
