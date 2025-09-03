package com.br.productmanager.application.dto.output;

import com.br.productmanager.domain.model.enums.ProductCategory;
import com.br.productmanager.infraestructure.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRecoveryDto (

        UUID id,
        String name,
        BigDecimal price,
        String description,
        ProductCategory category
) {

    public static ProductRecoveryDto fromProduct(ProductEntity product) {
        return new ProductRecoveryDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory()
        );
    }
}
