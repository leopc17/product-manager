package com.br.productmanager.infraestructure.adapter.controller;

import com.br.productmanager.application.dto.input.CreateProductDto;
import com.br.productmanager.application.dto.input.UpdateProductDto;
import com.br.productmanager.application.dto.output.ProductRecoveryDto;
import com.br.productmanager.domain.model.enums.ProductCategory;
import com.br.productmanager.domain.port.input.ProductServicePort;
import com.br.productmanager.infraestructure.entity.ProductEntity;
import com.br.productmanager.application.service.ProductServiceImpl;
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

    private final ProductServicePort productService;

    @Autowired
    public ProductController(ProductServicePort productServicePort) {
        this.productService = productServicePort;
    }

    @PostMapping
    public ResponseEntity<ProductRecoveryDto> create(@RequestBody @Valid CreateProductDto product) {
        ProductEntity productEntity = productService.create(new ProductEntity(product));

        ProductRecoveryDto productResponse = ProductRecoveryDto.fromProduct(productEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRecoveryDto> find(@PathVariable UUID id) {
        ProductEntity productEntity = productService.findById(id);

        ProductRecoveryDto productResponse = ProductRecoveryDto.fromProduct(productEntity);

        return ResponseEntity.ok(productResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProductRecoveryDto>> find() {
        List<ProductEntity> productsEntity = productService.findAll();

        List<ProductRecoveryDto> productsResponse = new ArrayList<>();
        for (ProductEntity p : productsEntity) {
            ProductRecoveryDto pr = ProductRecoveryDto.fromProduct(p);
            productsResponse.add(pr);
        }

        return ResponseEntity.ok(productsResponse);
    }

    @GetMapping("/categories/{category}")
    public ResponseEntity<List<ProductRecoveryDto>> find(@PathVariable String category) {
        ProductCategory enumCategory = ProductCategory.valueOf(category.toUpperCase());
        List<ProductEntity> productsEntity = productService.findAllByCategory(enumCategory);

        List<ProductRecoveryDto> productsResponse = new ArrayList<>();
        for (ProductEntity p : productsEntity) {
            ProductRecoveryDto pr = ProductRecoveryDto.fromProduct(p);
            productsResponse.add(pr);
        }

        return ResponseEntity.ok(productsResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductRecoveryDto> update(@PathVariable UUID id, @RequestBody @Valid UpdateProductDto newProduct) {
        ProductEntity newProductEntity = new ProductEntity(newProduct);
        newProductEntity.setId(id);

        ProductEntity productEntity = productService.update(id, newProductEntity);

        ProductRecoveryDto productResponse = ProductRecoveryDto.fromProduct(productEntity);

        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        productService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }
}
