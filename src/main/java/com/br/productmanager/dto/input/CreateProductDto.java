package com.br.productmanager.dto.input;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateProductDto (

    @NotBlank(message = "{not.blank.message}")
    @Size(min = 1, max = 150, message = "{size.message}")
    String name,

    @PositiveOrZero(message = "{positive.or.zero.message}")
    @Digits(integer = 10, fraction = 2, message = "{digits.message}")
    BigDecimal price,

    String description,

    @NotNull(message = "{not.null.message}")
    String category

) {}