package com.br.productmanager.model.entity;

import com.br.productmanager.dto.ProductRequestDto;
import com.br.productmanager.dto.ProductResponseDto;
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

    public Product(ProductRequestDto productRequestDto) {
        this.name = productRequestDto.name();
        this.price = productRequestDto.price();
        this.description = productRequestDto.description();
        this.category = productRequestDto.category();
    }

    public Product(ProductResponseDto productResponseDto) {
        this.name = productResponseDto.name();
        this.price = productResponseDto.price();
        this.description = productResponseDto.description();
        this.category = productResponseDto.category();
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
