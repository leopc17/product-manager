package com.br.productmanager.dto;

import com.br.productmanager.enums.ProductCategory;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequestDto (
    @NotBlank(message = "name can't be empty")
    @Size(max = 150, message = "name size must be less than or equal 150")
    String name,

    @PositiveOrZero(message = "price must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "price must have at most 10 integer digits and 2 decimal places.")
    BigDecimal price,

    String description,

    @NotNull(message = "product category is required")
    ProductCategory category

) {}