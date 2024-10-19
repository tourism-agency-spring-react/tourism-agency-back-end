package com.tourismagency.tourism_agency.service.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class TourismAgencyException extends RuntimeException{
    private HttpStatus status;

    private String description;

    private List<String> reasons;

    public TourismAgencyException(ApiError error) {
        this.status = error.getStatus();
        this.description = error.getMessage();
    }
}
