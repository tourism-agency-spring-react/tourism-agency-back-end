package com.tourismagency.tourism_agency.presentation.advice.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ErrorApiResponse(String description,
                               List<String> reasons) {

}
