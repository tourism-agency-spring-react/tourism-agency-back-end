package com.tourismagency.tourism_agency.presentation.advice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ErrorApiResponse {

    Date time = new Date();
    String message;
    String url;

    public ErrorApiResponse(String url, String message) {
        this.url = url.replace("uri=","");
        this.message = message;
    }
}
