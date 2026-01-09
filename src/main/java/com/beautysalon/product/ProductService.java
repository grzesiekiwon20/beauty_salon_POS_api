package com.beautysalon.product;

import com.beautysalon.category.SubCategory;
import com.beautysalon.product.dto.ProductRequest;
import com.beautysalon.product.dto.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    void saveProduct(ProductRequest productRequest, MultipartFile file);
    List<ProductResponse> findProductsList();
    ProductResponse findProductResponseByProductId(Long productId);
    List<ProductResponse> findProductsByCategoryId(Long categoryId);

    List<ProductResponse> findAllProductResponsesBySubCategory(SubCategory subCategory);
}
