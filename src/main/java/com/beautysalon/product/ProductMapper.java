package com.beautysalon.product;


import com.beautysalon.cartitem.CartItem;
import com.beautysalon.common.BaseEntity;
import com.beautysalon.file.FileUtils;
import com.beautysalon.product.dto.ProductRequest;
import com.beautysalon.product.dto.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductMapper {

    public Product mapProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setPrice(productRequest.price());
        product.setDiscount(productRequest.discount());
        product.setSpecialPrice(productRequest.specialPrice());
        product.setStockQuantity(productRequest.stockQuantity());
        product.setInventoryStatus(productRequest.inventoryStatus());
        product.setImage(productRequest.image());
        product.setProducts(new ArrayList<>());
        product.setOrderItems(new ArrayList<>());
        return product;
    }

    public ProductResponse mapProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setDiscount(product.getDiscount());
        productResponse.setSpecialPrice(product.getSpecialPrice());
        productResponse.setStockQuantity(product.getStockQuantity());
        productResponse.setInventoryStatus(product.getInventoryStatus());
        productResponse.setImage(FileUtils.readFileFromLocation(product.getImage()));
        productResponse.setCategory(product.getCategory());
        productResponse.setProducts(product.getProducts().stream().map(CartItem::getId).toList());
        productResponse.setOrderItems(product.getOrderItems().stream().map(BaseEntity::getId).toList());
        return productResponse;
    }
}
