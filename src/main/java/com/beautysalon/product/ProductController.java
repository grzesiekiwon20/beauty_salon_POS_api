package com.beautysalon.product;

import com.beautysalon.category.SubCategory;
import com.beautysalon.common.PageResponse;
import com.beautysalon.product.dto.ProductRequest;
import com.beautysalon.product.dto.ProductResponse;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Product", description = "The Product Api")
@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/addProduct/{categoryId}")
    public ResponseEntity<Long> createProduct(
            @PathVariable Long categoryId,
            @Valid @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(service.saveProduct(productRequest, categoryId));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long productId) {
        return ResponseEntity.ok(service.findProductResponseById(productId));
    }

    @PostMapping(value = "/cover/{product-id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadProductCoverPicture(
            @PathVariable("product-id") Long productId,
            @Parameter() @RequestPart("file") MultipartFile file) {
        service.uploadProductCoverPicture(file, productId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/byCategoryId/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategoryId(
            @PathVariable Long categoryId) {
        return ResponseEntity.ok(service.findProductsByCategoryId(categoryId));
    }

    @GetMapping("/page/")
    public ResponseEntity<PageResponse<ProductResponse>> getPageResponseProducts(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        return ResponseEntity.ok(service.findPageResponseProducts(page, size));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(service.findAllProductResponse());
    }

    
}
