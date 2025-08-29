package com.br.productmanager.dto.input;

import com.br.productmanager.enums.ProductCategory;
import com.br.productmanager.validation.annotation.EnumValue;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record UpdateProductDto (

    @NotBlank(message = "{not.blank.message}")
    @Size(min = 1, max = 150, message = "{size.message}")
    String name,

    @PositiveOrZero(message = "{positive.or.zero.message}")
    @Digits(integer = 10, fraction = 2, message = "{digits.message}")
    BigDecimal price,

    String description,

    @EnumValue(enumClass = ProductCategory.class, message = "{enum.value.message}")
    String category
) {}