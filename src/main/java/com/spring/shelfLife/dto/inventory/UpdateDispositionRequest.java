package com.spring.shelfLife.dto.inventory;

import com.spring.shelfLife.type.ItemDisposition;
import jakarta.validation.constraints.NotNull;

public record UpdateDispositionRequest(
        @NotNull ItemDisposition disposition
) {
}
