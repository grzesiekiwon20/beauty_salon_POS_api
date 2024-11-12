package com.beautysalon.product;


import com.beautysalon.category.Category;
import com.beautysalon.category.CategoryRepository;
import com.beautysalon.common.PageResponse;
import com.beautysalon.file.FileStorageService;
import com.beautysalon.product.dto.ProductRequest;
import com.beautysalon.product.dto.ProductResponse;
import com.beautysalon.type.Type;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;
    private final FileStorageService fileStorageService;
    private final CategoryRepository categoryRepository;

    public Long saveProduct(ProductRequest productRequest, Long categoryId) {
        Product product = mapper.mapProduct(productRequest);
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NullPointerException("No category found with Id: " + categoryId));

        if (!productRepository.existsByName(product.getName())) {
            product.setCategory(category);
            return productRepository.save(product).getId();
        } else {
            throw new RuntimeException("Product with name: " + productRequest.name() + "already exist");
        }
    }

    public ProductResponse findProductResponseById(Long productId) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new NullPointerException("No product found with Id: " + productId));
        return mapper.mapProductResponse(product);

    }

    public void uploadTypeCoverPicture(MultipartFile file, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("No product found with ID:: " + productId));
        var profilePicture = fileStorageService.saveFile(file, productId);
        product.setImage(profilePicture);
        productRepository.save(product);
    }

    public List<ProductResponse> findProductsByCategoryId(Long categoryId) {
        List<Product> productList = productRepository.findProductByCategoryId(categoryId);

        return productList.stream().map(mapper::mapProductResponse).toList();
    }

    public PageResponse<ProductResponse> findAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductResponse> productResponses = products
                .stream()
                .map(mapper::mapProductResponse)
                .toList();
        return new PageResponse<>(
                productResponses,
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.isFirst(),
                products.isLast()
        );
    }
}
