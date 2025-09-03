package com.br.productmanager.infraestructure.entity;

import com.br.productmanager.application.dto.input.CreateProductDto;
import com.br.productmanager.application.dto.input.UpdateProductDto;
import com.br.productmanager.application.dto.output.ProductRecoveryDto;
import com.br.productmanager.domain.model.enums.ProductCategory;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private BigDecimal price;
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    public ProductEntity() {

    }

    public ProductEntity(CreateProductDto createProductDto) {
        this.name = createProductDto.name();
        this.price = createProductDto.price();
        this.description = createProductDto.description();
        this.category = ProductCategory.valueOf(createProductDto.category());
    }

    public ProductEntity(UpdateProductDto updateProductDto) {
        this.name = updateProductDto.name();
        this.price = updateProductDto.price();
        this.description = updateProductDto.description();
        this.category = ProductCategory.valueOf(updateProductDto.category());
    }

    public ProductEntity(ProductRecoveryDto productRecoveryDto) {
        this.name = productRecoveryDto.name();
        this.price = productRecoveryDto.price();
        this.description = productRecoveryDto.description();
        this.category = productRecoveryDto.category();
    }

    public ProductEntity(UUID id, String name, BigDecimal price, String description, ProductCategory category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public ProductEntity(String name, BigDecimal price, String description, ProductCategory category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
