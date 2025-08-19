package com.br.productmanager.controller;

import com.br.productmanager.dto.ProductRequestDto;
import com.br.productmanager.dto.ProductResponseDto;
import com.br.productmanager.enums.ProductCategory;
import com.br.productmanager.model.entity.Product;
import com.br.productmanager.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductRequestDto product) {
        Product productEntity = productService.create(new Product(product));

        ProductResponseDto productResponse = ProductResponseDto.fromProduct(productEntity);

        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> find(@PathVariable UUID id) {
        Product productEntity = productService.findById(id);

        ProductResponseDto productResponse = ProductResponseDto.fromProduct(productEntity);

        return ResponseEntity.ok(productResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> find() {
        List<Product> productsEntity = productService.findAll();

        List<ProductResponseDto> productsResponse = new ArrayList<>();
        for (Product p : productsEntity) {
            ProductResponseDto pr = ProductResponseDto.fromProduct(p);
            productsResponse.add(pr);
        }

        return ResponseEntity.ok(productsResponse);
    }

    @GetMapping("/categories/{category}")
    public ResponseEntity<List<ProductResponseDto>> find(@PathVariable String category) {
        ProductCategory enumCategory = ProductCategory.valueOf(category.toUpperCase());
        List<Product> productsEntity = productService.findAllByCategory(enumCategory);

        List<ProductResponseDto> productsResponse = new ArrayList<>();
        for (Product p : productsEntity) {
            ProductResponseDto pr = ProductResponseDto.fromProduct(p);
            productsResponse.add(pr);
        }

        return ResponseEntity.ok(productsResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable UUID id, @RequestBody ProductRequestDto newProduct) {
        Product newProductEntity = new Product(newProduct);
        newProductEntity.setId(id);

        Product productEntity = productService.update(id, newProductEntity);

        ProductResponseDto productResponse = ProductResponseDto.fromProduct(productEntity);

        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        productService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }
}
