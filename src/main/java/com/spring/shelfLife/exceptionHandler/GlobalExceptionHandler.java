package com.spring.shelfLife.exceptionHandler;

import com.spring.shelfLife.exceptionHandler.customExceptions.DuplicateResourceException;
import com.spring.shelfLife.exceptionHandler.customExceptions.ResourceNotFoundException;
import com.spring.shelfLife.exceptionHandler.exceptionRecords.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Value("${ENVIRONMENT}")
    public String activeEnv;

    @ExceptionHandler({
            UsernameNotFoundException.class,
            BadCredentialsException.class,
            CredentialsExpiredException.class,
            DisabledException.class
    })
    public ResponseEntity<ApiError> handleAuthException(Exception ex, HttpServletRequest request) {
        logger.warn("Auth Exception : {}", ex.getClass().getName());
        ApiError apiError = ApiError.of(HttpStatus.UNAUTHORIZED,ex.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    @ExceptionHandler({
            ResourceNotFoundException.class
    })
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        logger.warn("Resource not found : {}", ex.getClass().getName());
        ApiError resourceNotFoundError = ApiError.of(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceNotFoundError);
    }

    @ExceptionHandler({DuplicateResourceException.class})
    public ResponseEntity<ApiError> handleDuplicateResourceException(DuplicateResourceException ex, HttpServletRequest request) {
        logger.warn("Duplicate Resource Exception : {}", ex.getClass().getName());
        ApiError duplicateResource = ApiError.of(HttpStatus.CONFLICT, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(duplicateResource);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        String message = String.join(",", errors);
        logger.warn("Method Argument Not Valid Exception : Error Stack : {} Message : {}",errors, message);
        ApiError methodArgumentsNotValid = ApiError.of(HttpStatus.BAD_REQUEST, message, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(methodArgumentsNotValid);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        logger.error("Illegal Argument Exception : {}", ex.getClass().getName());
        ApiError badRequestError = ApiError.of(HttpStatus.BAD_REQUEST,ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badRequestError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex, HttpServletRequest request) {
        logger.error("Unhandled Exception", ex);
        String path = "prod".equalsIgnoreCase(activeEnv) ? null : request.getRequestURI();
        ApiError internalServerError =  ApiError.of(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected Error occurred",
                path
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalServerError);
    }

}
