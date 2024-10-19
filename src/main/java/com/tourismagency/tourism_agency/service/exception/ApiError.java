package com.tourismagency.tourism_agency.service.exception;

import org.springframework.http.HttpStatus;

public enum ApiError {

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "The are attributes with wrong values"),
    BAD_FORMAT(HttpStatus.BAD_REQUEST, "The  message not have correct form"),
    CUSTOMER_NOT_FOUND(HttpStatus.NOT_FOUND, "Customer not found"),
    SALE_NOT_FOUND(HttpStatus.NOT_FOUND, "Sale not found"),
    EMPLOYEE_NOT_FOUND(HttpStatus.NOT_FOUND, "Employee not found"),
    TOURIST_PACKAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "Tourist package not found"),
    TOURIST_SERVICE_NOT_FOUND(HttpStatus.NOT_FOUND, "TouristService not found"),
    RESERVATION_WITH_SAME_ID(HttpStatus.CONFLICT, "There is a resource with the same ID"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),;

    private final HttpStatus status;

    private final String message;

    ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
