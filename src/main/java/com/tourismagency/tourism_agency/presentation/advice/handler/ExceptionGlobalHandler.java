package com.tourismagency.tourism_agency.presentation.advice.handler;

import com.tourismagency.tourism_agency.presentation.advice.response.ErrorApiResponse;
import com.tourismagency.tourism_agency.service.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionGlobalHandler{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ErrorApiResponse response = ErrorApiResponse.builder()
                .message(ex.getMessage())
                .url(request.getDescription(false))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
