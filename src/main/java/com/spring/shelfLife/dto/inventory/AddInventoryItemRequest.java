package com.spring.shelfLife.dto.inventory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AddInventoryItemRequest<units>(
        @NotBlank String productName,
        String barcode,
        String category,
        @NotNull @Positive Integer quantity,
        String unit,
        BigDecimal price,
        @NotNull LocalDate expirationDate
) {
}
