package com.beautysalon.product;


import com.beautysalon.category.Category;
import com.beautysalon.category.CategoryRepository;
import com.beautysalon.category.SubCategory;
import com.beautysalon.common.PageResponse;
import com.beautysalon.file.FileStorageService;
import com.beautysalon.product.dto.ProductRequest;
import com.beautysalon.product.dto.ProductResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;
    private final FileStorageService fileStorageService;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, ProductMapper mapper, FileStorageService fileStorageService, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.fileStorageService = fileStorageService;
        this.categoryRepository = categoryRepository;
    }

    public Long saveProduct(ProductRequest productRequest, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new NullPointerException("No category found"));
        Product product = mapper
                .mapProduct(productRequest);
        product.setCategory(category);
        return productRepository.save(product).getId();
    }

    public ProductResponse findProductResponseById(Long productId) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new NullPointerException("No product found with Id: " + productId));
        return mapper.mapProductResponse(product);

    }

    public void uploadProductCoverPicture(MultipartFile file, Long productId) {
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

    public PageResponse<ProductResponse> findPageResponseProducts(int page, int size) {
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


    public List<ProductResponse> findAllProductResponse(){
        return productRepository.findAll().stream().map(mapper::mapProductResponse).toList();
    }

}
