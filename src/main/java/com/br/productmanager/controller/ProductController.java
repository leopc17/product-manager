package com.br.productmanager.controller;

import com.br.productmanager.dto.input.CreateProductDto;
import com.br.productmanager.dto.output.ProductRecoveryDto;
import com.br.productmanager.enums.ProductCategory;
import com.br.productmanager.model.entity.Product;
import com.br.productmanager.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
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
    public ResponseEntity<ProductRecoveryDto> create(@RequestBody @Valid CreateProductDto product) {
        Product productEntity = productService.create(new Product(product));

        ProductRecoveryDto productResponse = ProductRecoveryDto.fromProduct(productEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRecoveryDto> find(@PathVariable UUID id) {
        Product productEntity = productService.findById(id);

        ProductRecoveryDto productResponse = ProductRecoveryDto.fromProduct(productEntity);

        return ResponseEntity.ok(productResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProductRecoveryDto>> find() {
        List<Product> productsEntity = productService.findAll();

        List<ProductRecoveryDto> productsResponse = new ArrayList<>();
        for (Product p : productsEntity) {
            ProductRecoveryDto pr = ProductRecoveryDto.fromProduct(p);
            productsResponse.add(pr);
        }

        return ResponseEntity.ok(productsResponse);
    }

    @GetMapping("/categories/{category}")
    public ResponseEntity<List<ProductRecoveryDto>> find(@PathVariable String category) {
        ProductCategory enumCategory = ProductCategory.valueOf(category.toUpperCase());
        List<Product> productsEntity = productService.findAllByCategory(enumCategory);

        List<ProductRecoveryDto> productsResponse = new ArrayList<>();
        for (Product p : productsEntity) {
            ProductRecoveryDto pr = ProductRecoveryDto.fromProduct(p);
            productsResponse.add(pr);
        }

        return ResponseEntity.ok(productsResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductRecoveryDto> update(@PathVariable UUID id, @RequestBody @Valid CreateProductDto newProduct) {
        Product newProductEntity = new Product(newProduct);
        newProductEntity.setId(id);

        Product productEntity = productService.update(id, newProductEntity);

        ProductRecoveryDto productResponse = ProductRecoveryDto.fromProduct(productEntity);

        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        productService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }
}
