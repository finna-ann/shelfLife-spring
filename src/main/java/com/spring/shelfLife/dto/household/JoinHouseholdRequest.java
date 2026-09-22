package com.spring.shelfLife.dto.household;

import jakarta.validation.constraints.NotBlank;

public record JoinHouseholdRequest(
        @NotBlank String inviteCode
) {
}
