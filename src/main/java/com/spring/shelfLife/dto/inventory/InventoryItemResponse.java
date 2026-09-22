package com.spring.shelfLife.dto.inventory;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record InventoryItemResponse(
        Long id,
        String productName,
        Integer quantity,
        String unit,
        BigDecimal price,
        LocalDate expirationDate,
        String disposition,
        String addedByUsername,
        Instant createdAt
) {
}
