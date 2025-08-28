package com.br.productmanager.dto.output;

import com.br.productmanager.enums.ProductCategory;
import com.br.productmanager.model.entity.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRecoveryDto (

        UUID id,
        String name,
        BigDecimal price,
        String description,
        ProductCategory category
) {

    public static ProductRecoveryDto fromProduct(Product product) {
        return new ProductRecoveryDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory()
        );
    }
}
