package com.br.productmanager.dto;

import com.br.productmanager.enums.ProductCategory;

import java.math.BigDecimal;

public record ProductRequestDto (
    String name,
    BigDecimal price,
    String description,
    ProductCategory category
    ) { }