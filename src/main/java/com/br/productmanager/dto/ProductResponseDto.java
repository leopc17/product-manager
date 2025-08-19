package com.br.productmanager.dto;

import com.br.productmanager.enums.ProductCategory;
import com.br.productmanager.model.entity.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDto (
        UUID id,
        String name,
        BigDecimal price,
        String description,
        ProductCategory category
) {
    public static ProductResponseDto fromProduct(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory()
        );
    }
}
