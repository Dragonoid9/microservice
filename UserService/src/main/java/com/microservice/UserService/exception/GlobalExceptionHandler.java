package com.microservice.UserService.exception;


import com.microservice.UserService.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value =ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException e) {
        String message = e.getMessage();

         return ResponseEntity.status(HttpStatus.NOT_FOUND)
                 .body(ApiResponse.builder()
                         .message(message)
                         .success(true)
                         .httpStatus(HttpStatus.NOT_FOUND)
                         .build());
    }
}
