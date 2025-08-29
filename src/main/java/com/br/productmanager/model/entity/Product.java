package com.br.productmanager.model.entity;

import com.br.productmanager.dto.input.CreateProductDto;
import com.br.productmanager.dto.output.ProductRecoveryDto;
import com.br.productmanager.enums.ProductCategory;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private BigDecimal price;
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    public Product() {

    }

    public Product(CreateProductDto createProductDto) {
        this.name = createProductDto.name();
        this.price = createProductDto.price();
        this.description = createProductDto.description();
        this.category = ProductCategory.valueOf(createProductDto.category());
    }

    public Product(ProductRecoveryDto productRecoveryDto) {
        this.name = productRecoveryDto.name();
        this.price = productRecoveryDto.price();
        this.description = productRecoveryDto.description();
        this.category = productRecoveryDto.category();
    }

    public Product(String name, BigDecimal price, String description, ProductCategory category) {
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
