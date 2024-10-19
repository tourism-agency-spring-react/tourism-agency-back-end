package com.tourismagency.tourism_agency.presentation.advice.handler;

import com.tourismagency.tourism_agency.presentation.advice.response.ErrorApiResponse;
import com.tourismagency.tourism_agency.service.exception.ApiError;
import com.tourismagency.tourism_agency.service.exception.TourismAgencyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TourismAgencyException.class)
    public ResponseEntity<ErrorApiResponse> duplicatedResource(TourismAgencyException e, WebRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new ErrorApiResponse(e.getDescription(), e.getReasons()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        List<String> reasons = new ArrayList<>();
        //Iteramos todos los campos que tienen un error y los vamos a agregar dentro del array con un formato definido por nosotros
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            reasons.add(String.format("%s - %s", fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return ResponseEntity.status(ApiError.VALIDATION_ERROR.getStatus()).body(new ErrorApiResponse(ApiError.VALIDATION_ERROR.getMessage(), reasons));
    }
}
