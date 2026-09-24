package com.spring.shelfLife.dto.exceptionRecords;

import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public record ApiError(
        HttpStatus status,
        String error,
        String message,
        String path,
        OffsetDateTime timestamp
) {
    public static ApiError of(HttpStatus status, String message, String path) {
        return new ApiError(status, status.getReasonPhrase(), message, path, OffsetDateTime.now(ZoneOffset.UTC));
    }
}