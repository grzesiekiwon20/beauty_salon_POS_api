package com.beautysalon.product;


import com.beautysalon.category.Category;
import com.beautysalon.category.CategoryRepository;
import com.beautysalon.category.SubCategory;
import com.beautysalon.file.FileStorageService;
import com.beautysalon.product.dto.ProductRequest;
import com.beautysalon.product.dto.ProductResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FileStorageService fileStorageService;
    private final ProductMapper mapper;
    private final CategoryRepository categoryRepository;


    @Override
    public void saveProduct(final ProductRequest productRequest,final MultipartFile file) {
        final Category category = categoryRepository.findById(productRequest.getCategory().getId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found."));

        Product product = mapper.mapProduct(productRequest);
        product.setCategory(category);

        final Product savedProduct = productRepository.save(product);
        final Long productId = savedProduct.getId();

        if (!file.isEmpty()) {
            String fileName = fileStorageService.saveFile(file, productId);
            savedProduct.setImage(fileName);
            productRepository.save(savedProduct);
        }
    }

    @Override
    public List<ProductResponse> findProductsList() {
        return productRepository.findAll().stream().map(mapper::mapProductResponse).toList();
    }

    @Override
    public ProductResponse findProductResponseByProductId(final Long productId) {
        return productRepository.findById(productId)
                .map(mapper::mapProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
    }

    @Override
    public List<ProductResponse> findProductsByCategoryId(final Long categoryId) {
        return productRepository.findByCategoryId(categoryId)
                .stream()
                .map(mapper::mapProductResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> findAllProductResponsesBySubCategory(final SubCategory subCategory) {
        return productRepository.findProductsBySubCategory(subCategory).stream().map(mapper::mapProductResponse).toList();
    }


}
