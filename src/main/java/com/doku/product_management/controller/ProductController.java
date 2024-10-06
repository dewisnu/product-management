package com.doku.product_management.controller;

import com.doku.product_management.dto.ProductWithoutId;
import com.doku.product_management.entity.Product;
import com.doku.product_management.repository.ProductRepository;
import com.doku.product_management.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> addProduct(@Valid @RequestBody ProductWithoutId productDto) {
        Product newProduct = new Product();
        newProduct.setName(productDto.getName());
        newProduct.setPrice(productDto.getPrice());
        newProduct.setQuantity(productDto.getQuantity());

        Product savedProduct = productRepository.save(newProduct);
        ApiResponse<Product> response = new ApiResponse<>(true, "Product added successfully", savedProduct);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        ApiResponse<List<Product>> response = new ApiResponse<>(true, "Products fetched successfully", products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            ApiResponse<Product> response = new ApiResponse<>(true, "Product fetched successfully", product.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Product> response = new ApiResponse<>(false, "Product not found", 1304, null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductWithoutId productDto) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDto.getName());
                    product.setPrice(productDto.getPrice());
                    product.setQuantity(productDto.getQuantity());
                    Product updatedProduct = productRepository.save(product);
                    ApiResponse<Product> response = new ApiResponse<>(true, "Product updated successfully", updatedProduct);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    ApiResponse<Product> response = new ApiResponse<>(false, "Product not found", 1304, null);
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Integer id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    ApiResponse<Void> response = new ApiResponse<>(true, "Product deleted successfully", null);
                    return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> {
                    ApiResponse<Void> response = new ApiResponse<>(false, "Product not found", 1304, null);
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                });
    }
}