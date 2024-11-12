package com.beautysalon.product;


import com.beautysalon.common.PageResponse;
import com.beautysalon.product.dto.ProductRequest;
import com.beautysalon.product.dto.ProductResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Tag(name = "Product", description = "The Product Api")
@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/addProduct/{categoryId}")
    public ResponseEntity<Long> createProduct(
            @RequestBody ProductRequest productRequest,
            @PathVariable Long categoryId
    ){
        return ResponseEntity.ok(service.saveProduct(productRequest, categoryId));
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long productId
    ){
        return ResponseEntity.ok(service.findProductResponseById(productId));
    }

    @PostMapping(value = "/cover/{product-id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadProductCoverPicture(
            @PathVariable("product-id") Long productId,
            @Parameter()
            @RequestPart("file") MultipartFile file
    ) {
        service.uploadTypeCoverPicture(file, productId);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/byCategoryId/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategoryId(
            @PathVariable Long categoryId
    ){
        return ResponseEntity.ok(service.findProductsByCategoryId(categoryId));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ProductResponse>> findAllProducts(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(service.findAllProducts(page, size));
    }
}
