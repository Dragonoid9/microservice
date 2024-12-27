package com.microservice.AuthService.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(){
        super("Resource already exists !!!");
    }

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
