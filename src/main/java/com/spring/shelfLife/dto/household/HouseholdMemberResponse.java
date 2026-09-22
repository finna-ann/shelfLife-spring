package com.spring.shelfLife.dto.household;

public record HouseholdMemberResponse(
        Long userId,
        String username,
        String role
) {
}
