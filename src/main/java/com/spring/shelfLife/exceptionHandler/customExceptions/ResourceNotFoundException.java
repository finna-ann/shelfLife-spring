package com.spring.shelfLife.exceptionHandler.customExceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
