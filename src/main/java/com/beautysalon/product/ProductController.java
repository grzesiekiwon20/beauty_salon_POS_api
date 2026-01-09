package com.beautysalon.product;

import com.beautysalon.product.dto.ProductRequest;
import com.beautysalon.product.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl service;

    @GetMapping("/addNew")
    public String addNewProduct(Model model) {
        ProductRequest productRequest = new ProductRequest();
        model.addAttribute("product", productRequest);
        return "/products/productsmng";
    }

    @PostMapping("/save")
    public String saveProduct(
            @ModelAttribute("product") @Valid ProductRequest product,
            @RequestParam("file") MultipartFile file) {
        service.saveProduct(product, file);
        return "redirect:/";
    }

    @GetMapping("/byCategory/{categoryId}")
    public String viewProductsByCategoryId(
           @PathVariable Long categoryId,
           Model model
    ) {
        model.addAttribute("allProducts", service.findProductsByCategoryId(categoryId));
        return "/products/products";
    }

    @GetMapping("/byId/{productId}")
    public String getProductResponseById( Model model,
            @PathVariable Long productId) {
        model.addAttribute("productDetails", service.findProductResponseByProductId(productId));
        return "/products/products";
    }
    @GetMapping("/all")
    public String getAllProducts(
            Model model
    ){
        model.addAttribute("allProducts", service.findProductsList());
        return "/products/products";
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
        ProductResponse product = service.findProductResponseByProductId(id);

        if (product.image() == null || product.image().length == 0) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(product.image(), headers, HttpStatus.OK);
    }
}


