package com.br.productmanager.controller;

import com.br.productmanager.enums.ProductCategory;
import com.br.productmanager.model.entity.Product;
import com.br.productmanager.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.ok(productService.create(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> find(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> find() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/categories/{category}")
    public ResponseEntity<List<Product>> find(@PathVariable String category) {
        ProductCategory enumCategory = ProductCategory.valueOf(category.toUpperCase());
        return ResponseEntity.ok(productService.findAllByCategory(enumCategory));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable UUID id, @RequestBody Product newProduct) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, newProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        productService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }
}
