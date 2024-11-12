package com.beautysalon.product;


import com.beautysalon.common.BaseEntity;
import com.beautysalon.file.FileUtils;
import com.beautysalon.product.dto.ProductRequest;
import com.beautysalon.product.dto.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductMapper {

    public Product mapProduct(ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .discount(productRequest.discount())
                .specialPrice(productRequest.specialPrice())
                .stockQuantity(productRequest.stockQuantity())
                .image(productRequest.image())
                .products(new ArrayList<>())
                .orderItems(new ArrayList<>())
                .build();
    }

    public ProductResponse mapProductResponse(Product product){
        return ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .specialPrice(product.getSpecialPrice())
                .stockQuantity(product.getStockQuantity())
                .image(FileUtils.readFileFromLocation(product.getImage()))
                .category(product.getCategory())
                .products(product.getProducts().stream().map(BaseEntity::getId).toList())
                .orderItems(product.getOrderItems().stream().map(BaseEntity::getId).toList())
                .build();
    }
}
